package com.barlocalizer.maps;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;

import com.barlocalizer.R;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

/**
 * Classe contenant les points à ajouter sur la Map.
 * 
 * @author martinelli-b
 * 
 */
public class MapsDataItemizedOverlay extends ItemizedOverlay<OverlayItem> {

    /** Liste de point de la Map. */
    private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();

    /** Activity de l'application android. */
    private Activity mActivity = null;

    /**
     * Constructeur.
     * 
     * @param defaultMarker
     *            Marqueur initial
     * @param activity
     *            L'Activity de l'application Android
     */
    public MapsDataItemizedOverlay(final Drawable defaultMarker, final Activity activity) {
        super(boundCenter(defaultMarker));
        mActivity = activity;
    }

    /**
     * Ajout d'un point à la liste.
     * 
     * @param overlay
     *            Le point a ajouter
     */
    public void addOverlay(final OverlayItem overlay) {
        mOverlays.add(overlay);
        populate();
    }

    @Override
    public int size() {
        return mOverlays.size();
    }

    @Override
    protected OverlayItem createItem(final int i) {
        return mOverlays.get(i);
    }

    @Override
    protected boolean onTap(final int index) {
        OverlayItem item = mOverlays.get(index);
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle(item.getTitle());
        builder.setMessage(item.getSnippet());
        builder.setPositiveButton(R.string.geocalbutton, new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialog, final int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
        return true;
    }
}
