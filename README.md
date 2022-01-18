[![](https://jitpack.io/v/tk.nkduy/Dialog.svg)](https://jitpack.io/#tk.nkduy/Dialog)

Dialog
==========
Simple and advanced dialog solution.

- Uses normal view as dialog
- Provides expandable option
- Multiple positioning
- Built-in options for easy implementation

##### Dialog provides android L dialog animation

##### Dialog provides 3 position:
- Top : Dialog will appear at top with animation
- Center : Dialog will appear in the center with animation
- Bottom : Dialog will appear at the bottom of the screen with animation

##### Dialog provides 3 content types:
- ListHolder : Items will be shown in a listview
- GridHolder : Items will be shown in a gridview
- ViewHolder : Your customized view will be shown in the content

### Gradle
```groovy
implementation 'tk.nkduy:dialog:1.0@aar'
```

### Usage
Use the builder to create the dialog.

Basic usage
```java
Dialog dialog = Dialog.newDialog(this)
  .setAdapter(adapter)
  .setOnItemClickListener(new OnItemClickListener() {
    @Override
    public void onItemClick(Dialog dialog, Object item, View view, int position) {
    }
  })
  .setExpanded(true)  // This will enable the expand feature, (similar to android L share dialog)
  .create();
dialog.show();
```

### More options
Enable expand animation same as Android L share dialog
```java
.setExpanded(true) // default is false, only works for grid and list
```
Set expand animation default height
```java
.setExpanded(true, 300)
```

Select different holder.

- Use ListView as content holder, note that this is default content type.
```java
setContentHolder(new ListHolder())
```
- Use ViewHolder as content holder if you want to use a custom view for your dialog. Pass resource id
```java
.setContentHolder(new ViewHolder(R.layout.content))
```
or pass view itself
```java
.setContentHolder(new ViewHolder(view))
```
- Use GridHolder if you want to use GridView for the dialog. You must set column number.
```java
.setContentHolder(new GridHolder(COLUMN_NUMBER))
```
- Get the holder view, ListView, GridView or your custom view
```java
View view = dialog.getHolderView();
```
- Set dialog position. BOTTOM (default), TOP or CENTER. You can also combine other Gravity options.
```java
.setGravity(Gravity.CENTER)
```
- Define if the dialog is cancelable and should be closed when back pressed or out of dialog is clicked
```java
.setCancelable(true)
```
- Set Adapter, this adapter will be used to fill the content for ListHolder and GridHolder. This is required if the content holder is ListHolder or GridHolder. It is not required if the content holder is ViewHolder.
```java
.setAdapter(adapter);
```
- Set an item click listener when list or grid holder is chosen. In that way you can have callbacks when one of your items is clicked
```java
.setOnItemClickListener(new OnItemClickListener() {
  @Override
  public void onItemClick(Dialog dialog, Object item, View view, int position) {
  }
})
```
- Set a global click listener to you dialog in order to handle all the possible click events. You can then identify the view by using its id and handle the correct behaviour. Only views which has id will trigger this event.
```java
.setOnClickListener(new OnClickListener() {
    @Override
    public void onClick(Dialog dialog, View view) {

    }
})
```
- Add margins to your dialog. They are set to 0 except when gravity is center. In that case basic margins are applied
```java
.setMargin(left, top, right, bottom)
```
- Set padding to the holder
```java
.setPadding(left, top, right, bottom)
```
- Set the footer view using the id of the layout resource
```java
.setFooter(R.layout.footer)
```
or use view
```java
.setFooter(view)
```
- Get the footer view
```java
View view = dialog.getFooterView();
```
- Set the header view using the id of the layout resource
```java
.setHeader(R.layout.header)
```
or use view
```java
.setHeader(view)
```
- Get the header view
```java
View view = dialog.getHeaderView();
```
- Set animation resources
```java
.setInAnimation(R.anim.abc_fade_in)
.setOutAnimation(R.anim.abc_fade_out)
```
- Set width and height for the content
```java
.setContentWidth(ViewGroup.LayoutParams.WRAP_CONTENT)  // or any custom width ie: 300
.setContentHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
```

- Dismiss Listener, triggered when the dialog is dismissed
```java
.setOnDismissListener(new OnDismissListener() {
    @Override
    public void onDismiss(Dialog dialog) {

    }
})
```

- Cancel Listener, triggered when the dialog is cancelled by back button or clicking outside
```java
.setOnCancelListener(new OnCancelListener() {
    @Override
    public void onCancel(Dialog dialog) {

    }
})
```

- BackPress Listener, triggered when the back button is pressed
```java
.setOnBackPressListener(new OnBackPressListener() {
    @Override
    public void onBackPressed(Dialog dialog) {

    }
})
```

- Change content container background, as default white
```java
.setContentBackgroundResource(resource)
```

- Change overlay container background, as default it's semi-transparent black
```java
.setOverlayBackgroundResource(resource)
```

#### License
<pre>
Copyright 2021 NKDuy

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
</pre>
