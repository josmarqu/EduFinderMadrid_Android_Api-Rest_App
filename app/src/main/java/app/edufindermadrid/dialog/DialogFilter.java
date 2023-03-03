package app.edufindermadrid.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import app.edufindermadrid.R;

public class DialogFilter extends DialogFragment {
    OnDialogListener mListener;
    private EditText etLat;
    private EditText etLon;
    private EditText etDis;

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_filter, null);

        etLat = v.findViewById(R.id.etLat);
        etLon = v.findViewById(R.id.etLon);
        etDis = v.findViewById(R.id.etDis);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.MyAlertDialogStyle);
        builder.setView(v);

        builder.setTitle(R.string.edu_center_filter);
        builder.setPositiveButton(getString(R.string.apply), null);
        builder.setNegativeButton(getString(R.string.cancel), ((dialogInterface, i) -> dialogInterface.dismiss()));

        AlertDialog ad = builder.create();
        ad.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.dialog_bg));
        ad.setCanceledOnTouchOutside(false);

        ad.setOnShowListener(dialogInterface -> {
            Button btnPositive = ad.getButton(AlertDialog.BUTTON_POSITIVE);
            btnPositive.setOnClickListener(view -> {
                double lat = Double.parseDouble(etLat.getText().toString());
                double lon = Double.parseDouble(etLon.getText().toString());
                double dis = Double.parseDouble(etDis.getText().toString());
                mListener.onDialogPositiveClick(lat, lon, dis);
                ad.dismiss();
            });
        });
        return ad;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context + getString(R.string.implement_OnDialogList));
        }
    }

    @Override
    public void onDetach() {
        if (mListener != null) {
            mListener = null;
        }
        super.onDetach();
    }
}

