package com.entage.nrd.entage.Subscriptions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.entage.nrd.entage.entage.OnActivityListener;
import com.entage.nrd.entage.Models.ItemWithDataUser;
import com.entage.nrd.entage.R;
import com.entage.nrd.entage.utilities_1.ViewItemFragment;

public class EntajiPageSubscriptionActivity extends AppCompatActivity implements OnActivityListener {
    private static final String TAG = "EntajiPageSubscActivity";

    private Context mContext ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_layout);
        mContext = EntajiPageSubscriptionActivity.this;
        Log.d(TAG, "onCreate: started.");

        onActivityListener_noStuck(new EntajiPageSubscriptionContainer(), getIntent().getExtras());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d(TAG, "onActivityResult: " + requestCode + ", " + resultCode);

        if(requestCode == 99){
            if(resultCode == 99){
                Intent mIntent = this.getIntent();
                startActivity(mIntent); // restart th activity
                finish();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    // ** OnActivityListener
    @Override
    public void onActivityListener(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.containerEntage, fragment);
        transaction.addToBackStack(getString(R.string.view_personal_fragment)) ;
        transaction.commit();
    }

    @Override
    public void onActivityListener(Fragment fragment, Bundle bundle) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        fragment.setArguments(bundle);
        transaction.replace(R.id.containerEntage, fragment);
        transaction.addToBackStack(getString(R.string.view_personal_fragment)) ;
        transaction.commit();
    }

    @Override
    public void onActivityListener_noStuck(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.containerEntage, fragment);
        transaction.commit();
    }

    @Override
    public void onActivityListener_noStuck(Fragment fragment, Bundle bundle) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        fragment.setArguments(bundle);
        transaction.replace(R.id.containerEntage, fragment);
        transaction.commitAllowingStateLoss(); // transaction.commit(); -->java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState
    }

    @Override
    public void onGridImageSelected(ItemWithDataUser item) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("item", item);
        onActivityListener(new ViewItemFragment(), bundle);
    }

}
