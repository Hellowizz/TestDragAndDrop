package com.example.hellowizz.testdraganddrop;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    ImageView m_pikachu;
    ImageView m_pikachu2;
    ImageView m_violet;
    ImageView m_violet2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_pikachu = findViewById(R.id.Pikachu);
        m_pikachu2 = findViewById(R.id.Pikachu2);

        m_pikachu.setOnTouchListener(new MyTouchListener1());
        m_pikachu2.setOnTouchListener(new MyTouchListener2());
        m_pikachu2.setX(80);

        m_violet = findViewById(R.id.violet);
        m_violet.setOnDragListener(new MyDragListener());
        m_violet.setX(250);
        m_violet.setY(550);

        m_violet2 = findViewById(R.id.violet2);
        //m_violet2.setOnDragListener(new MyDragListener2());
        m_violet2.setX(250);
        m_violet2.setY(850);
    }


    private class MyTouchListener1 implements OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("pikachu", "Pikachu");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            } else {
                return false;
            }
        }
    }

    private class MyTouchListener2 implements OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("pikachu", "Pikachu2");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            } else {
                return false;
            }
        }
    }

    private class MyDragListener implements OnDragListener {
        Drawable enterShape = getResources().getDrawable(R.drawable.violet);
        Drawable normalShape = getResources().getDrawable(R.drawable.shape);
        Drawable pikachuShape = getResources().getDrawable(R.drawable.pikachu);

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackgroundDrawable(enterShape);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    //Dropped, reassign View to ViewGroup
                    View view = (View) event.getLocalState();
                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);
                    LinearLayout container = (LinearLayout) v;  /// BUG ICI ???
                    //container.addView(view);
                    //view.setVisibility(View.VISIBLE);

                    //Intent gameActivityIntent = new Intent(MainActivity.this, NextActivity.class);
                    //startActivity(gameActivityIntent);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackgroundDrawable(null);
                    break;
                default:
                    break;
            }
            return true;
        }
    }
    private class MyDragListener2 implements OnDragListener {
        Drawable enterShape = getResources().getDrawable(R.drawable.violet);
        Drawable normalShape = getResources().getDrawable(R.drawable.shape);

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackgroundDrawable(enterShape);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackgroundDrawable(normalShape);
                    break;
                case DragEvent.ACTION_DROP:
                    //Dropped, reassign View to ViewGroup
                    //View view = (View) event.getLocalState();
                    //ViewGroup owner = (ViewGroup) view.getParent();
                    //owner.removeView(view);
                    //LinearLayout container = (LinearLayout) v;
                    //container.addView(view);
                    //view.setVisibility(View.VISIBLE);

                    ClipData data = event.getClipData();
                    if(data != null) {
                        Log.i("text", data.getItemAt(0).toString());
                        Log.i("label", data.getDescription().toString());
                    }

                    if(data.getItemAt(0).toString().equals("ClipData.Item { T:Pikachu2 }"))
                        Log.i("Info", "C'EST LE SECOND PIKACHUUUUUUUUUUUUU");

                    Log.i("Truc", "on a dropppp");

                    //Intent gameActivityIntent = new Intent(MainActivity.this, NextActivity.class);
                    //startActivity(gameActivityIntent);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackgroundDrawable(null);
                    break;
                default:
                    break;
            }
            return true;
        }
    }

}
