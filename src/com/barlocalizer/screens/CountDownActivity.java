package com.barlocalizer.screens;

import java.util.List;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;

import com.barlocalizer.R;
import com.barlocalizer.maps.MapsDataItemizedOverlay;
import com.barlocalizer.services.StatusNotificationService;
import com.barlocalizer.threads.ThreadPopupCheers;
import com.barlocalizer.threads.ThreadRefreshCountDown;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

/**
 * CountDown Activity.
 * 
 * @author Pierre-Marie Dhaussy
 * @author Benjamin Martinelli
 * @author Gael "La Grosse" Lire
 */
public class CountDownActivity extends MapActivity {

    /** . */
    private static final int LATITUDE_CALLAGHAN = 45193683;

    /** . */
    private static final int LONGITUDE_CALLAGHAN = 5729333;

    /** Thread permettant l'affichage d'un message de Cheer par p�riode d�finie. */
    private ThreadPopupCheers threadPopupCheers = null;

    /** Thread permettant le refreshissement du CountDown par p�riode d�finie. */
    private ThreadRefreshCountDown threadCountDown = null;

    /** Permet d'ajouter des ordres de modification de l'interface. */
    private Handler handler = null;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.countdown);
        MapView mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);

        // Liste des points de la map � afficher
        List<Overlay> mapOverlays = mapView.getOverlays();
        Drawable drawable = getResources().getDrawable(R.drawable.icon);
        MapsDataItemizedOverlay mapsData = new MapsDataItemizedOverlay(drawable, this);

        addMapPoints(mapsData, mapOverlays);

        handler = new Handler();
    }

    /**
     * Ajoute des points sur la Map Google.
     * 
     * @param map
     *            La map � alimenter
     * @param mapOverlays
     *            Liste des points de la vraie Map Google
     */
    private void addMapPoints(final MapsDataItemizedOverlay map, final List<Overlay> mapOverlays) {
        GeoPoint point = new GeoPoint(LATITUDE_CALLAGHAN, LONGITUDE_CALLAGHAN);
        OverlayItem overlayItem = new OverlayItem(point, getString(R.string.geocaltitle),
                getString(R.string.geocalsnippet));

        map.addOverlay(overlayItem);
        mapOverlays.add(map);
    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        // D�marrage du service
        startService(new Intent(this, StatusNotificationService.class));

        threadCountDown = new ThreadRefreshCountDown(this);
        threadCountDown.start();

        threadPopupCheers = new ThreadPopupCheers(this);
        threadPopupCheers.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stop();
    }

    /**
     * Arr�te tous les �l�ments li�s � l'activit�.
     */
    private void stop() {
        stopServices();
        stopThreads();
    }

    /**
     * Arr�te les services li�s � l'activit�.
     */
    private void stopServices() {
        stopService(new Intent(this, StatusNotificationService.class));
    }

    /**
     * Arr�te les threads li�s � l'activit�.
     */
    private void stopThreads() {
        threadCountDown.setRun(false);
        threadPopupCheers.setRun(false);
    }

    /**
     * @return the handler
     */
    public Handler getHandler() {
        return handler;
    }

}
