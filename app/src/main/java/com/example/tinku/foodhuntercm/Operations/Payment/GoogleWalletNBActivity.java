package com.example.tinku.foodhuntercm.Operations.Payment;

/* Import appropriate libraries */
import com.example.tinku.foodhuntercm.*;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wallet.Cart;
import com.google.android.gms.wallet.FullWallet;
import com.google.android.gms.wallet.FullWalletRequest;
import com.google.android.gms.wallet.LineItem;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.NotifyTransactionStatusRequest;
import com.google.android.gms.wallet.Wallet;
import com.google.android.gms.wallet.WalletConstants;
import com.google.android.gms.wallet.fragment.BuyButtonText;
import com.google.android.gms.wallet.fragment.Dimension;
import com.google.android.gms.wallet.fragment.SupportWalletFragment;
import com.google.android.gms.wallet.fragment.WalletFragmentInitParams;
import com.google.android.gms.wallet.fragment.WalletFragmentMode;
import com.google.android.gms.wallet.fragment.WalletFragmentOptions;
import com.google.android.gms.wallet.fragment.WalletFragmentStyle;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class GoogleWalletNBActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener {

    /* Variables for handling Payment activity */
    private GoogleApiClient mGoogleApiClient;
    private SupportWalletFragment mWalletFragment;
    private SupportWalletFragment mXmlWalletFragment;
    String price;
    private MaskedWallet mMaskedWallet;
    private FullWallet mFullWallet;
    public static final int MASKED_WALLET_REQUEST_CODE = 888;
    public static final int FULL_WALLET_REQUEST_CODE = 889;
    public static final String WALLET_FRAGMENT_ID = "wallet_fragment";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /* Create layout of Payment */
        super.onCreate(savedInstanceState);

        /* Get the price information passed from the intent */
        Intent intent = getIntent();
        price = intent.getStringExtra("price");

        /* Start building wallet fragment */
        mWalletFragment = (SupportWalletFragment) getSupportFragmentManager().findFragmentByTag(WALLET_FRAGMENT_ID);
        WalletFragmentInitParams startParams;
        WalletFragmentInitParams.Builder startParamsBuilder = WalletFragmentInitParams.newBuilder()
                .setMaskedWalletRequest(generateMaskedWalletRequest())
                .setMaskedWalletRequestCode(MASKED_WALLET_REQUEST_CODE);
        startParams = startParamsBuilder.build();

        /* If fragment is null, set fragment options here */
        if(mWalletFragment == null) {
            WalletFragmentStyle walletFragmentStyle = new WalletFragmentStyle()
                    .setBuyButtonText(BuyButtonText.BUY_WITH_GOOGLE)
                    .setBuyButtonWidth(Dimension.MATCH_PARENT);
            WalletFragmentOptions walletFragmentOptions = WalletFragmentOptions.newBuilder()
                    .setEnvironment(WalletConstants.ENVIRONMENT_SANDBOX)
                    .setFragmentStyle(walletFragmentStyle)
                    .setTheme(WalletConstants.THEME_HOLO_LIGHT)
                    .setMode(WalletFragmentMode.BUY_BUTTON)
                    .build();
            mWalletFragment = SupportWalletFragment.newInstance(walletFragmentOptions);
            mWalletFragment.initialize(startParams);
        }

        /* Begin transaction using fragment manager */
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.wallet_button_holder, mWalletFragment, WALLET_FRAGMENT_ID)
                .commit();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Wallet.API, new Wallet.WalletOptions.Builder()
                        .setEnvironment(WalletConstants.ENVIRONMENT_SANDBOX)
                        .setTheme(WalletConstants.THEME_HOLO_LIGHT)
                        .build())
                .build();

        /* Inflate the layout here */
        setContentView(R.layout.activity_google_wallet_nb);
    }


    /* On start of this activity connect to Google API client */
    public void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        /* Get the result of the activity here */
        super.onActivityResult(requestCode,resultCode,data);

        switch (requestCode) {
            case MASKED_WALLET_REQUEST_CODE:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        mMaskedWallet =
                                data.getParcelableExtra(WalletConstants.EXTRA_MASKED_WALLET);
                        break;
                    case Activity.RESULT_CANCELED:
                        break;
                    default:
                        Toast.makeText(this, "An Error Occurred", Toast.LENGTH_LONG).show();
                        break;
                }
                break;
            case FULL_WALLET_REQUEST_CODE:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        mFullWallet =
                                data.getParcelableExtra(WalletConstants.EXTRA_FULL_WALLET);
                        Toast.makeText(this, mFullWallet.getProxyCard().getPan().toString(), Toast.LENGTH_LONG).show();

                        Wallet.Payments.notifyTransactionStatus(mGoogleApiClient,
                                generateNotifyTransactionStatusRequest(mFullWallet.getGoogleTransactionId(),
                                        NotifyTransactionStatusRequest.Status.SUCCESS));

                        break;
                    default:
                        Toast.makeText(this, "An Error Occurred", Toast.LENGTH_LONG).show();
                        break;
                }
                break;
            case WalletConstants.RESULT_ERROR:
                Toast.makeText(this, "An Error Occurred", Toast.LENGTH_LONG).show();
                break;
        }
    }

    /* Notify the status of transaction here */
    public static NotifyTransactionStatusRequest generateNotifyTransactionStatusRequest(
            String googleTransactionId, int status) {
        return NotifyTransactionStatusRequest.newBuilder()
                .setGoogleTransactionId(googleTransactionId)
                .setStatus(status)
                .build();
    }

    /* Generate masked wall request here */
    private MaskedWalletRequest generateMaskedWalletRequest() {
        MaskedWalletRequest maskedWalletRequest =
                MaskedWalletRequest.newBuilder()
                        .setMerchantName("Google I/O Codelab")
                        .setPhoneNumberRequired(true)
                        .setShippingAddressRequired(true)
                        .setCurrencyCode("USD")
                        .setShouldRetrieveWalletObjects(true)
                        .setEstimatedTotalPrice("10.00")
                        .setCart(Cart.newBuilder()
                                .setCurrencyCode("USD")
                                .setTotalPrice("10.00")
                                .addLineItem(LineItem.newBuilder()
                                        .setCurrencyCode("USD")
                                        .setDescription("Google I/O Sticker")
                                        .setQuantity("1")
                                        .setUnitPrice("10.00")
                                        .setTotalPrice("10.00")
                                        .build())
                                .build())
                        .build();

        return maskedWalletRequest;
    }

    /* Generate full wallet request here */
    private FullWalletRequest generateFullWalletRequest(String googleTransactionId) {
        FullWalletRequest fullWalletRequest = FullWalletRequest.newBuilder()
                .setGoogleTransactionId(googleTransactionId)
                .setCart(Cart.newBuilder()
                        .setCurrencyCode("USD")
                        .setTotalPrice("10.10")
                        .addLineItem(LineItem.newBuilder()
                                .setCurrencyCode("USD")
                                .setDescription("Google I/O Sticker")
                                .setQuantity("1")
                                .setUnitPrice("10.00")
                                .setTotalPrice("10.00")
                                .build())
                        .addLineItem(LineItem.newBuilder()
                                .setCurrencyCode("USD")
                                .setDescription("Tax")
                                .setRole(LineItem.Role.TAX)
                                .setTotalPrice(".10")
                                .build())
                        .build())
                .build();
        return fullWalletRequest;
    }

    /* Request full wallet */
    public void requestFullWallet(View view) {
        if (mGoogleApiClient.isConnected()){
            Wallet.Payments.loadFullWallet(mGoogleApiClient,
                    generateFullWalletRequest(mMaskedWallet.getGoogleTransactionId()),
                    FULL_WALLET_REQUEST_CODE);
        }
    }

    /* Placeholders to override onconnect methods */
    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
