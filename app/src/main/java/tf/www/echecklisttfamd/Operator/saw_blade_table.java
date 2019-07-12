package tf.www.echecklisttfamd.Operator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import tf.www.echecklisttfamd.R;

public class saw_blade_table extends DialogFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.popwindow, container, false);
        getDialog().setTitle("simple dialog");

        return view;
    }
}
