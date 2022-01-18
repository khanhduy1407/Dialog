package tk.nkduy.dialog;

import androidx.annotation.NonNull;

/**
 * Dialog tries to listen back press actions.
 */
public interface OnBackPressListener {

    /**
     * Invoked when Dialog receives any back press button event.
     */
    void onBackPressed(@NonNull Dialog dialog);

}
