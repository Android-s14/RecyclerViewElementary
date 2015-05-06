# RecyclerViewElementary
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-RecyclerViewElementary-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/1735)

The idea of the project is to make construction of simple RecyclerViews as easy as writing one line of code and supplying a data structure.
Why spend time implementing multiple classes if you only need a simple RecyclerView?
Currently RecyclerViews containing TextViews and ImageViews are supported with default or custom row layouts.

You are very welcome to offer improvements and feature requests.
## How to use
1. Add the library to your project dependencies.

    ```
    dependencies {
        compile fileTree(dir: 'libs', include: ['*.jar'])
        compile 'com.github.android-s14:rve:1.1.4'
    }
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
- `setItemAnimator(RecyclerView.ItemAnimator)` - add custom animation for the list items

#### Supplying your RecyclerView
Now you can also provide your own RecyclerView: either made programmatically or defined in XML:

```java
RecyclerView recyclerView = (RecyclerView) layout.findViewById(R.id.recycler_view);
new RecyclerViewBuilder(this).using(recyclerView).setData(imageData).build();
```

```java
View layout = getLayoutInflater().inflate(R.layout.activity_main_recycler, null);
new RecyclerViewBuilder(this).using(R.id.recycler_view, layout).setData(imageData).build();
```

You are not required to catch the returned RecyclerView - it is the same instance as supplied.

## Coming soon
- [x] basic construction of purely textual RecyclerViews
- [x] setting a custom LayoutManager
- [x] setting an onClickListener
- [x] setting a custom layout from layout resources (implemented for textual RecyclerViews)
- [x] support for images in data structures
- [ ] other customizations (some are already implemented: margins, padding, corner radii, etc.)
- [ ] your requested features

## Screenshots
This is what you get just by providing a simple data structure class containing two text fields and one drawable field and using the most basic construction process `new RecyclerViewBuilder(this).setData(imageData).build`.

<img src="/screenshots/image_recycler.png" width="250")/>

This is a simplistic textual RecyclerView constructed with mere `new RecyclerViewBuilder(this).setData(data).setLayoutManager(new GridLayoutManager(this,2)).setRowLayout(R.layout.text_row_layout.build()`.

<img src="/screenshots/text_recycler.png" width="250")/>



