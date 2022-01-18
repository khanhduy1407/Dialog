package tk.nkduy.dialog;

import android.app.Activity;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;

import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DialogBuilder {
    private static final int INVALID = -1;

    private final int[] margin = new int[4];
    private final int[] padding = new int[4];
    private final int[] outMostMargin = new int[4];
    private final FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM
    );

    private BaseAdapter adapter;
    private Context context;
    private View footerView;
    private View headerView;
    private Holder holder;
    private int gravity = Gravity.BOTTOM;
    private OnItemClickListener onItemClickListener;
    private OnClickListener onClickListener;
    private OnDismissListener onDismissListener;
    private OnCancelListener onCancelListener;
    private OnBackPressListener onBackPressListener;

    private boolean isCancelable = true;
    private int contentBackgroundResource = android.R.color.white;
    private int headerViewResourceId = INVALID;
    private boolean fixedHeader = false;
    private int footerViewResourceId = INVALID;
    private boolean fixedFooter = false;
    private int inAnimation = INVALID;
    private int outAnimation = INVALID;
    private boolean expanded;
    private int defaultContentHeight;
    private int overlayBackgroundResource = R.color.dialogplus_black_overlay;

    private DialogBuilder() {
    }

    /**
     * Initialize the builder with a valid context in order to inflate the dialog
     */
    DialogBuilder(@NonNull Context context) {
        this.context = context;
        Arrays.fill(margin, INVALID);
    }

    /**
     * Set the adapter that will be used when ListHolder or GridHolder are passed
     */
    public DialogBuilder setAdapter(@NonNull BaseAdapter adapter) {
        this.adapter = adapter;
        return this;
    }

    /**
     * Set the footer view using the id of the layout resource
     */
    public DialogBuilder setFooter(int resourceId) {
        return setFooter(resourceId, false);
    }

    /**
     * Set the footer view using the id of the layout resource
     *
     * @param fixed is used to determine whether footer should be fixed or not. Fixed if true, scrollable otherwise
     */
    public DialogBuilder setFooter(int resourceId, boolean fixed) {
        this.footerViewResourceId = resourceId;
        this.fixedFooter = fixed;
        return this;
    }

    /**
     * Sets the given view as footer.
     */
    public DialogBuilder setFooter(@NonNull View view) {
        return setFooter(view, false);
    }

    /**
     * Sets the given view as footer.
     *
     * @param fixed is used to determine whether footer should be fixed or not. Fixed if true, scrollable otherwise
     */
    public DialogBuilder setFooter(@NonNull View view, boolean fixed) {
        this.footerView = view;
        this.fixedFooter = fixed;
        return this;
    }

    /**
     * Set the header view using the id of the layout resource
     */
    public DialogBuilder setHeader(int resourceId) {
        return setHeader(resourceId, false);
    }

    /**
     * Set the header view using the id of the layout resource
     *
     * @param fixed is used to determine whether header should be fixed or not. Fixed if true, scrollable otherwise
     */
    public DialogBuilder setHeader(int resourceId, boolean fixed) {
        this.headerViewResourceId = resourceId;
        this.fixedHeader = fixed;
        return this;
    }

    /**
     * Set the header view using a view
     */
    public DialogBuilder setHeader(@NonNull View view) {
        return setHeader(view, false);
    }

    /**
     * Set the header view using a view
     *
     * @param fixed is used to determine whether header should be fixed or not. Fixed if true, scrollable otherwise
     */
    public DialogBuilder setHeader(@NonNull View view, boolean fixed) {
        this.headerView = view;
        this.fixedHeader = fixed;
        return this;
    }

    /**
     * Define if the dialog is cancelable and should be closed when back pressed or click outside is pressed
     */
    public DialogBuilder setCancelable(boolean isCancelable) {
        this.isCancelable = isCancelable;
        return this;
    }

    /**
     * Set the content of the dialog by passing one of the provided Holders
     */
    public DialogBuilder setContentHolder(@NonNull Holder holder) {
        this.holder = holder;
        return this;
    }

    /**
     * Use setBackgroundResource
     */
    @Deprecated public DialogBuilder setBackgroundColorResId(int resourceId) {
        return setContentBackgroundResource(resourceId);
    }

    /**
     * Set background color for your dialog. If no resource is passed 'white' will be used
     */
    public DialogBuilder setContentBackgroundResource(int resourceId) {
        this.contentBackgroundResource = resourceId;
        return this;
    }

    public DialogBuilder setOverlayBackgroundResource(int resourceId) {
        this.overlayBackgroundResource = resourceId;
        return this;
    }

    /**
     * Set the gravity you want the dialog to have among the ones that are provided
     */
    public DialogBuilder setGravity(int gravity) {
        this.gravity = gravity;
        params.gravity = gravity;
        return this;
    }

    /**
     * Customize the in animation by passing an animation resource
     */
    public DialogBuilder setInAnimation(int inAnimResource) {
        this.inAnimation = inAnimResource;
        return this;
    }

    /**
     * Customize the out animation by passing an animation resource
     */
    public DialogBuilder setOutAnimation(int outAnimResource) {
        this.outAnimation = outAnimResource;
        return this;
    }

    /**
     * Add margins to your outmost view which contains everything. As default they are 0
     * are applied
     */
    public DialogBuilder setOutMostMargin(int left, int top, int right, int bottom) {
        this.outMostMargin[0] = left;
        this.outMostMargin[1] = top;
        this.outMostMargin[2] = right;
        this.outMostMargin[3] = bottom;
        return this;
    }

    /**
     * Add margins to your dialog. They are set to 0 except when gravity is center. In that case basic margins
     * are applied
     */
    public DialogBuilder setMargin(int left, int top, int right, int bottom) {
        this.margin[0] = left;
        this.margin[1] = top;
        this.margin[2] = right;
        this.margin[3] = bottom;
        return this;
    }

    /**
     * Set paddings for the dialog content
     */
    public DialogBuilder setPadding(int left, int top, int right, int bottom) {
        this.padding[0] = left;
        this.padding[1] = top;
        this.padding[2] = right;
        this.padding[3] = bottom;
        return this;
    }

    /**
     * Set an item click listener when list or grid holder is chosen. In that way you can have callbacks when one
     * of your items is clicked
     */
    public DialogBuilder setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
        return this;
    }

    /**
     * Set a global click listener to you dialog in order to handle all the possible click events. You can then
     * identify the view by using its id and handle the correct behaviour
     */
    public DialogBuilder setOnClickListener(@Nullable OnClickListener listener) {
        this.onClickListener = listener;
        return this;
    }

    public DialogBuilder setOnDismissListener(@Nullable OnDismissListener listener) {
        this.onDismissListener = listener;
        return this;
    }

    public DialogBuilder setOnCancelListener(@Nullable OnCancelListener listener) {
        this.onCancelListener = listener;
        return this;
    }

    public DialogBuilder setOnBackPressListener(@Nullable OnBackPressListener listener) {
        this.onBackPressListener = listener;
        return this;
    }

    public DialogBuilder setExpanded(boolean expanded) {
        this.expanded = expanded;
        return this;
    }

    public DialogBuilder setExpanded(boolean expanded, int defaultContentHeight) {
        this.expanded = expanded;
        this.defaultContentHeight = defaultContentHeight;
        return this;
    }

    public DialogBuilder setContentHeight(int height) {
        params.height = height;
        return this;
    }

    public DialogBuilder setContentWidth(int width) {
        params.width = width;
        return this;
    }

    /**
     * Create the dialog using this builder
     */
    @NonNull public Dialog create() {
        getHolder().setBackgroundResource(getContentBackgroundResource());
        return new Dialog(this);
    }

    @Nullable public View getFooterView() {
        return Utils.getView(context, footerViewResourceId, footerView);
    }

    @Nullable public View getHeaderView() {
        return Utils.getView(context, headerViewResourceId, headerView);
    }

    @NonNull public Holder getHolder() {
        if (holder == null) {
            holder = new ListHolder();
        }
        return holder;
    }

    @NonNull public Context getContext() {
        return context;
    }

    public BaseAdapter getAdapter() {
        return adapter;
    }

    public Animation getInAnimation() {
        int res = (inAnimation == INVALID) ? Utils.getAnimationResource(this.gravity, true) : inAnimation;
        return AnimationUtils.loadAnimation(context, res);
    }

    public Animation getOutAnimation() {
        int res = (outAnimation == INVALID) ? Utils.getAnimationResource(this.gravity, false) : outAnimation;
        return AnimationUtils.loadAnimation(context, res);
    }

    public FrameLayout.LayoutParams getContentParams() {
        if (expanded) {
            params.height = getDefaultContentHeight();
        }
        return params;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public FrameLayout.LayoutParams getOutmostLayoutParams() {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        );
        params.setMargins(outMostMargin[0], outMostMargin[1], outMostMargin[2], outMostMargin[3]);
        return params;
    }

    public boolean isCancelable() {
        return isCancelable;
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public OnDismissListener getOnDismissListener() {
        return onDismissListener;
    }

    public OnCancelListener getOnCancelListener() {
        return onCancelListener;
    }

    public OnBackPressListener getOnBackPressListener() {
        return onBackPressListener;
    }

    public int[] getContentMargin() {
        int minimumMargin = context.getResources().getDimensionPixelSize(R.dimen.dialogplus_default_center_margin);
        for (int i = 0; i < margin.length; i++) {
            margin[i] = getMargin(this.gravity, margin[i], minimumMargin);
        }
        return margin;
    }

    public int[] getContentPadding() {
        return padding;
    }

    public int getDefaultContentHeight() {
        Activity activity = (Activity) context;
        Display display = activity.getWindowManager().getDefaultDisplay();
        int displayHeight = display.getHeight() - Utils.getStatusBarHeight(activity);
        if (defaultContentHeight == 0) {
            defaultContentHeight = (displayHeight * 2) / 5;
        }
        return defaultContentHeight;
    }

    public int getOverlayBackgroundResource() {
        return overlayBackgroundResource;
    }

    public int getContentBackgroundResource() {
        return contentBackgroundResource;
    }

    /**
     * Get margins if provided or assign default values based on gravity
     *
     * @param gravity       the gravity of the dialog
     * @param margin        the value defined in the builder
     * @param minimumMargin the minimum margin when gravity center is selected
     * @return the value of the margin
     */
    private int getMargin(int gravity, int margin, int minimumMargin) {
        switch (gravity) {
            case Gravity.CENTER:
                return (margin == INVALID) ? minimumMargin : margin;
            default:
                return (margin == INVALID) ? 0 : margin;
        }
    }

    public boolean isFixedHeader() {
        return fixedHeader;
    }

    public boolean isFixedFooter() {
        return fixedFooter;
    }
}
