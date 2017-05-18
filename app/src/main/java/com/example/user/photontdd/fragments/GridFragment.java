package com.example.user.photontdd.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.example.user.photontdd.R;
import com.example.user.photontdd.utils.FindPath;
import com.example.user.photontdd.utils.GridMatrix;
import com.example.user.photontdd.utils.GridPath;
import com.example.user.photontdd.utils.RowCost;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by tSravani on 5/14/2017.
 */
public class GridFragment extends Fragment {

    int width, height;
    Button runButton;
    List<List<EditText>> editTextGrid;
    LinearLayout gridLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_test, null);
        runButton = (Button) view.findViewById(R.id.findPath);
        width = getArguments().getInt("width", 3);
        height = getArguments().getInt("height", 3);
        gridLayout = (LinearLayout) view.findViewById(R.id.grid_section);

        generateGridItems();
        runButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResult(startTest());
            }
        });
        return view;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.add("FillRandomData").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                fillWithTestData();
                return true;
            }
        }).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    private void showResult(GridPath solutionPath) {

        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
            }
        });

        String res;
        res = solutionPath.terminates() ? "Yes\n" : "No\n";
        res += solutionPath.getCost() + "\n";
        res += "[";
        for (RowCost step : solutionPath) {
            res += step.getRow() + " ";
        }
        res += "]";

        alertDialog.setTitle("Result");
        alertDialog.setMessage(res);
        alertDialog.show();
    }

    private void generateGridItems() {
        editTextGrid = new ArrayList<List<EditText>>();

        for (int widthIndex = 0; widthIndex < width; widthIndex++) {
            List<EditText> column = new ArrayList<EditText>();
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            for (int heightIndex = 0; heightIndex < height; heightIndex++) {

                EditText editText = new EditText(getContext());
                editText.setTextSize(24);
                editText.setWidth(100);
                editText.setHeight(100);
                editText.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED);
                column.add(editText);
                linearLayout.addView(editText);
            }
            editTextGrid.add(column);
            gridLayout.addView(linearLayout);
        }
    }

    private GridPath startTest() {
        GridMatrix pathmatrix = new GridMatrix();

        for (int widthIndex = 0; widthIndex < width; widthIndex++) {
            List<Integer> column = new ArrayList<Integer>();

            for (int heightIndex = 0; heightIndex < height; heightIndex++) {
                int value;
                if (editTextGrid.get(widthIndex).get(heightIndex).getText().length() == 0) {
                    value = 0;
                } else {
                    value = Integer.parseInt(editTextGrid.get(widthIndex).get(heightIndex).getText().toString());
                }
                column.add(value);
            }

            pathmatrix.addColumn(column);
        }

        return new FindPath(pathmatrix).findPath();
    }


    private void fillWithTestData() {

        Random rand = new Random();


        for (int widthIndex = 0; widthIndex < width; widthIndex++) {

            for (int heightIndex = 0; heightIndex < height; heightIndex++) {
                 editTextGrid.get(widthIndex).get(heightIndex).setText(""+rand.nextInt(50));

            }

        }
    }


    public static GridFragment newInstance(int w, int h) {
        GridFragment frag = new GridFragment();
        Bundle b = new Bundle();
        b.putInt("width", w);
        b.putInt("height", h);
        frag.setArguments(b);
        return frag;
    }
}
