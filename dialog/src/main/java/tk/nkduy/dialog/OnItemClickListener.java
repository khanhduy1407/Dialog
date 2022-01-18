package tk.nkduy.dialog;

import androidx.annotation.NonNull;
import android.view.View;

/**
 * It is used to propagate click events for {@link ListHolder} and {@link GridHolder}
 *
 * <p>For each item click, {@link #onItemClick(Dialog, Object, View, int)} will be invoked</p>
 */
public interface OnItemClickListener {

    void onItemClick(@NonNull Dialog dialog, @NonNull Object item, @NonNull View view, int position);

}
