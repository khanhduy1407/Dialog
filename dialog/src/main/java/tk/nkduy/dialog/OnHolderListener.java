package tk.nkduy.dialog;

import androidx.annotation.NonNull;
import android.view.View;

public interface OnHolderListener {

    void onItemClick(@NonNull Object item, @NonNull View view, int position);

}
