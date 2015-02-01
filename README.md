# RecyclerViewElementary
The idea of the project is to make construction of simple RecyclerViews as easy as writing one line of code and supplying a data structure.
Why spend time implementing multiple classes if you only need a simple RecyclerView?
Currently RecyclerViews containing TextViews and ImageViews are supported with default or custom row layouts.

You are very welcome to offer improvements and feature requests.
## How to use
1. Add rve.aar file to your project dependencies.
You may need to use this method to reference an .aar file http://geekgarage.dad3zero.net/local-aar-android-library/
2. Add RecyclerView and CardView dependencies to your project:

    ```
     compile 'com.android.support:cardview-v7:21.0.0'
     compile 'com.android.support:recyclerview-v7:21.0.0'
    ```
3. Implement `DataModel` interface to describe your data structure.
4. Build your RecyclerView:

    ```java
    List<DataModel> data = getData(); //your data
    RecyclerView view = new RecyclerViewBuilder(this).setData(data).build();
    ```

5. Add RecyclerView to your layout.

You can find an example of usage in the `sample` module.

### Supported customizations
- `setLayoutManager(LayoutManager)` - provide your layout manager, `LinearLayoutManager` with vertical orientation is used by default
- `setRowLayout(@LayoutRes int)` - provide your custom row layout, default behaviour is stacking all your Drawable and String fields from the data structure in a horizontal linear view inside a card view
- `setListener(View.OnClickListener)` - provide your onClickListener for RecyclerView members
- `setCardMargins(int, int, int, int)` - set margins for both default and custom row layouts (default = 5dp)
- `setCardPadding(int, int, int, int)` - set padding for the default row layout only (for custom set up in XML) (default = 10dp)
- `setCardCornerRadii(float)` - set corner radii for the default row layout (for custom CardViews set up in XML) (default = 10dp)

## Coming soon
- [x] basic construction of purely textual RecyclerViews
- [x] setting a custom LayoutManager
- [x] setting an onClickListener
- [x] setting a custom layout from layout resources (implemented for textual RecyclerViews)
- [x] support for images in data structures
- [ ] other customizations (some are already implemented: margins, padding, corner radii, etc.)
- [ ] your requested features

## Screenshots
This is what you get just by providing a simple data structure class containing two text fields and one drawable field and using the most basic construction process `new RecyclerViewBuilder(this).setData(imageData).build`:

![Image RecyclerView](/screenshots/image_recycler.png)

This is a simplistic textual RecyclerView constructed with merely `new RecyclerViewBuilder(this).setData(data).setLayoutManager(new GridLayoutManager(this,2)).setRowLayout(R.layout.text_row_layout).build()`:

![Text RecyclerView](/screenshots/text_recycler.png)