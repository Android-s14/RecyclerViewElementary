# RecyclerViewElementary
The final idea is to obtain a fully ready RecyclerView (returned programmatically) in one line of code.

A very initial stage of development. There are plans to leave the most basic function for constructing a fully ready RecyclerView while adding different functions returning more customized RecyclerViews (with custom layout managers, different data structures, custom row layouts, etc.)

## How to use
1. Add rve.aar file to your project dependencies.
You may need to use this method to reference an .aar file http://geekgarage.dad3zero.net/local-aar-android-library/
2. Implement `DataModel` interface to describe your data structure.
3. Build your RecyclerView:

    ```java
    List<DataModel> data = getData(); //your data
    RecyclerView view = new RecyclerViewBuilder(this).setData(data).build();
    ```

4. Add RecyclerView to your layout.

You can find an example of usage in the `sample` module.

### Supported customizations
- `setLayoutManager(LayoutManager)` - provide your layout manager, `LinearLayoutManager` with vertical orientation is used by default
- `setRowLayout(@LayoutRes int)` - provide your custom row layout, default behaviour is stacking all your String fields from the data structure in a horizontal linear view inside a card view
- `setListener(View.OnClickListener)` - provide your onClickListener for RecyclerView members
- `setCardMargins(int, int, int, int)` - set margins for both default and custom row layouts (default = 5dp)
- `setCardPadding(int, int, int, int)` - set padding for the default row layout only (for custom set up in XML) (default = 10dp)
- `setCardCornerRadii(float)` - set corner radii for the default row layout (for custom CardViews set up in XML) (default = 10dp)

## Coming soon
- [x] basic construction of purely textual RecyclerViews
- [x] setting a custom LayoutManager
- [x] setting an onClickListener
- [x] setting a custom layout from layout resources (implemented for textual RecyclerViews)
- [ ] support for images in data structures
- [ ] other customizations (some are already implemented: margins, padding, corner radii, etc.)