package com.gamerole.common.adapter.slimadapter;

import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.gamerole.common.base.App;
import com.gamerole.common.glide.GlideApp;
import com.gamerole.common.glide.GlideRoundTransformCenterCrop;


/**
 * Created by linshuaibin on 22/12/2016.
 */
public class DefaultViewInjector implements IViewInjector<DefaultViewInjector> {
    private static RequestOptions optionsNormal;
    private static RequestOptions optionsCircle;


    static {
        optionsCircle = new RequestOptions().format(DecodeFormat.PREFER_ARGB_8888).circleCrop();
    }

    static {
        optionsNormal = new RequestOptions().format(DecodeFormat.PREFER_ARGB_8888);
    }

    private SlimViewHolder viewHolder;
    private int pos;

    public DefaultViewInjector(SlimViewHolder viewHolder) {
        this.viewHolder = viewHolder;
    }

    public DefaultViewInjector(SlimViewHolder viewHolder, int pos) {
        this.viewHolder = viewHolder;
        this.pos = pos;
    }

    @Override
    public final <T extends View> T findViewById(int id) {
        return (T) viewHolder.id(id);
    }

    @Override
    public DefaultViewInjector tag(int id, Object object) {
        findViewById(id).setTag(object);
        return this;
    }

    @Override
    public DefaultViewInjector text(int id, int res) {
        TextView view = findViewById(id);
        view.setText(res);
        return this;
    }

    @Override
    public int pos() {
        return pos;
    }

    @Override
    public DefaultViewInjector text(int id, CharSequence charSequence) {
        TextView view = findViewById(id);
        view.setText(charSequence);
        return this;
    }

    @Override
    public DefaultViewInjector typeface(int id, Typeface typeface, int style) {
        TextView view = findViewById(id);
        view.setTypeface(typeface, style);
        return this;
    }

    @Override
    public DefaultViewInjector typeface(int id, Typeface typeface) {
        TextView view = findViewById(id);
        view.setTypeface(typeface);
        return this;
    }

    @Override
    public DefaultViewInjector textColor(int id, int color) {
        TextView view = findViewById(id);
        view.setTextColor(color);
        return this;
    }

    @Override
    public DefaultViewInjector textColor(int id, ColorStateList color) {
        TextView view = findViewById(id);
        view.setTextColor(color);
        return this;
    }

    @Override
    public DefaultViewInjector textSize(int id, int sp) {
        TextView view = findViewById(id);
        view.setTextSize(TypedValue.COMPLEX_UNIT_SP, sp);
        return this;
    }

    @Override
    public DefaultViewInjector alpha(int id, float alpha) {
        View view = findViewById(id);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            view.setAlpha(alpha);
        } else {
            AlphaAnimation animation = new AlphaAnimation(alpha, alpha);
            animation.setDuration(0);
            animation.setFillAfter(true);
            view.startAnimation(animation);
        }
        return this;
    }

    @Override
    public DefaultViewInjector hint(int id, CharSequence charSequence) {
        TextView view = findViewById(id);
        view.setHint(charSequence);
        return this;
    }

    @Override
    public DefaultViewInjector image(int id, int res) {
        ImageView view = findViewById(id);
        view.setImageResource(res);
        return this;
    }


    @Override
    public DefaultViewInjector imageCircle(int id, Object url) {
        GlideApp.with(App.INSTANCE).load(url).apply(optionsCircle).into((ImageView) findViewById(id));
        return this;
    }


    @Override
    public DefaultViewInjector image(int id, String url) {
        GlideApp.with(App.INSTANCE).load(url).apply(optionsNormal).into((ImageView) findViewById(id));

        return this;
    }

    @Override
    public DefaultViewInjector imageRound(int id, String url, int size) {
        GlideApp.with(App.INSTANCE).load(url).apply(new RequestOptions().format(DecodeFormat.PREFER_ARGB_8888).transform(new GlideRoundTransformCenterCrop(size))).into((ImageView) findViewById(id));
        return this;
    }

    @Override
    public DefaultViewInjector image(int id, Drawable drawable) {
        ImageView view = findViewById(id);
        view.setImageDrawable(drawable);
        return this;
    }

    @Override
    public DefaultViewInjector background(int id, int res) {
        View view = findViewById(id);
        view.setBackgroundResource(res);
        return this;
    }

    @Override
    public DefaultViewInjector backgroundColor(int id, int res) {
        View view = findViewById(id);
        view.setBackgroundColor(res);
        return this;
    }

    @SuppressWarnings("deprecation")
    @Override
    public DefaultViewInjector background(int id, Drawable drawable) {
        View view = findViewById(id);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
        return this;
    }

    @Override
    public DefaultViewInjector visible(int id) {
        findViewById(id).setVisibility(View.VISIBLE);
        return this;
    }


    @Override
    public DefaultViewInjector invisible(int id) {
        findViewById(id).setVisibility(View.INVISIBLE);
        return this;
    }

    @Override
    public DefaultViewInjector gone(int id) {
        findViewById(id).setVisibility(View.GONE);
        return this;
    }

    @Override
    public DefaultViewInjector visibility(int id, int visibility) {
        findViewById(id).setVisibility(visibility);
        return this;
    }

    @Override
    public <V extends View> DefaultViewInjector with(int id, Action<V> action) {
        action.action((V) findViewById(id));
        return this;
    }

    @Override
    public DefaultViewInjector clicked(int id, View.OnClickListener listener) {
        findViewById(id).setOnClickListener(listener);
        return this;
    }

    @Override
    public DefaultViewInjector longClicked(int id, View.OnLongClickListener listener) {
        findViewById(id).setOnLongClickListener(listener);
        return this;
    }

    @Override
    public DefaultViewInjector enable(int id, boolean enable) {
        findViewById(id).setEnabled(enable);
        return this;
    }

    @Override
    public DefaultViewInjector enable(int id) {
        findViewById(id).setEnabled(true);
        return this;
    }

    @Override
    public DefaultViewInjector disable(int id) {
        findViewById(id).setEnabled(false);
        return this;
    }

    @Override
    public DefaultViewInjector checked(int id, boolean checked) {
        Checkable view = findViewById(id);
        view.setChecked(checked);
        return this;
    }

    @Override
    public DefaultViewInjector checkChangeListener(int id, CompoundButton.OnCheckedChangeListener checkedChangeListener) {
        CheckBox view = findViewById(id);
        view.setOnCheckedChangeListener(checkedChangeListener);
        return this;

    }

    @Override
    public DefaultViewInjector selected(int id, boolean selected) {
        findViewById(id).setSelected(selected);
        return this;
    }

    @Override
    public DefaultViewInjector pressed(int id, boolean pressed) {
        findViewById(id).setPressed(pressed);
        return this;
    }

    @Override
    public DefaultViewInjector adapter(int id, RecyclerView.Adapter adapter) {
        RecyclerView view = findViewById(id);
        view.setAdapter(adapter);
        return this;
    }

    @Override
    public DefaultViewInjector adapter(int id, Adapter adapter) {
        AdapterView view = findViewById(id);
        view.setAdapter(adapter);
        return this;
    }

    @Override
    public DefaultViewInjector layoutManager(int id, RecyclerView.LayoutManager layoutManager) {
        RecyclerView view = findViewById(id);
        view.setLayoutManager(layoutManager);
        return this;
    }

    @Override
    public DefaultViewInjector addView(int id, View... views) {
        ViewGroup viewGroup = findViewById(id);
        for (View view : views) {
            viewGroup.addView(view);
        }
        return this;
    }

    @Override
    public DefaultViewInjector addView(int id, View view, ViewGroup.LayoutParams params) {
        ViewGroup viewGroup = findViewById(id);
        viewGroup.addView(view, params);
        return this;
    }

    @Override
    public DefaultViewInjector removeAllViews(int id) {
        ViewGroup viewGroup = findViewById(id);
        viewGroup.removeAllViews();
        return this;
    }

    @Override
    public DefaultViewInjector removeView(int id, View view) {
        ViewGroup viewGroup = findViewById(id);
        viewGroup.removeView(view);
        return this;
    }

    @Override
    public <V extends View> DefaultViewInjector itemView(Action<V> action) {
        action.action((V) viewHolder.itemView);
        return this;
    }
}
