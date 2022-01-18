package tk.nkduy.dialog;

import androidx.annotation.NonNull;

/**
 * Dialog will use this listener to propagate cancel events when back button is pressed.
 */
public interface OnCancelListener {

    void onCancel(@NonNull Dialog dialog);
}
