# CapTextView
A user interface element that displays text to the user.


<img src="https://github.com/datanapps/CapTextView/blob/main/screen/screen1.png" height="500" width="250"> 


## Use Captextview as custom textview in your project 


```
 <datanapps.captextview.CapTextView
        android:id="@+id/text2"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"

        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        android:padding="5dp"
        app:layout_constraintTop_toBottomOf="@+id/text1"
        
        app:capsDropNumber="2"
        app:capFont="@font/eb_garamond_regular"
        app:capTextColor="@color/purple_700"
        app:capTextSize="@dimen/cap_text_size"

        app:bodyTextColor="@color/purple_700"
        app:bodyTextSize="@dimen/body_text_size"
        app:bodyTextFont="@font/eb_garamond_regular"
        />

```

### Its facilitate to set attribites in xml 
### for cap text view:

```
        app:capsDropNumber="2"
        app:capFont="@font/eb_garamond_regular"
        app:capTextColor="@color/purple_700"
        app:capTextSize="@dimen/cap_text_size"
   
```

### for body text view:

```
   
        app:bodyTextColor="@color/purple_700"
        app:bodyTextSize="@dimen/body_text_size"
        app:bodyTextFont="@font/eb_garamond_regular"
        
        
```


### There is custom method is also available.
  
  
   fun setCapTextFont(@NonNull fontId:Int)
   
   fun setCapTextAttrColor(@NonNull color:Int)
   
   fun setBodyTextFont(@NonNull fontId:Int)
   
   fun setHtmlText(text: String, isCapText:Boolean)
   
   fun setHtmlText(text: String)
   
   fun setTextScale(sizeInPx: Int)
   
   
    -----------------------------
    
Hope it will help. Thanks
