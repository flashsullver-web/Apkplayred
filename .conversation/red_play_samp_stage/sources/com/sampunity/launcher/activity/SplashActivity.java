package com.sampunity.launcher.activity;

import a.b32;
import a.bq1;
import a.ct1;
import a.dr0;
import a.eh2;
import a.eq1;
import a.er2;
import a.fq1;
import a.gi1;
import a.gq1;
import a.gt1;
import a.h42;
import a.hw0;
import a.hw1;
import a.jp;
import a.k92;
import a.n81;
import a.od2;
import a.or;
import a.pt0;
import a.qe1;
import a.r0;
import a.s02;
import a.u00;
import a.u5;
import a.u80;
import a.v5;
import a.wh2;
import a.x22;
import a.xu0;
import a.y00;
import a.y22;
import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.PathInterpolator;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.sampunity.game.R;
import com.sampunity.launcher.activity.SplashActivity;
import com.sampunity.launcher.download.DownloadService;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes2.dex */
public class SplashActivity extends AppCompatActivity implements GLSurfaceView.Renderer {
    public String m;
    public ProgressDialog n;
    public boolean o = false;
    public GLSurfaceView p;
    public FirebaseAnalytics q;
    public v5 r;

    /* JADX INFO: Access modifiers changed from: private */
    public void CheckGpu() {
        String string = getSharedPreferences(s02.j("PUMsGeJUWCY=\n", "WjNZWoM3MEM=\n"), 0).getString(s02.j("HId4\n", "e/cNoBIWzxU=\n"), null);
        String str = jp.f509a;
        try {
            jp.b = getSharedPreferences(s02.j("qafkrtDGHhQ=\n", "zteR7bGldnE=\n"), 0).getString(s02.j("TMnfv6yuWilU2Ma1qp4=\n", "OKyn287xPEg=\n"), null);
        } catch (Throwable unused) {
            jp.b = null;
        }
        if (string != null) {
            jp.f509a = string;
            GetServerList();
            return;
        }
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.detect_gpu);
        if (constraintLayout.getWidth() <= 0 || constraintLayout.getHeight() <= 0) {
            constraintLayout.getViewTreeObserver().addOnGlobalLayoutListener(new fq1(this, constraintLayout));
        } else {
            initializeGLSurfaceView(constraintLayout);
        }
    }

    private void CheckVersion() {
        if (isFinishing() || isDestroyed()) {
            return;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) getSystemService(s02.j("RoA17/Rjgd9Thi/4\n", "Je9bgZEA9bY=\n"))).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting()) {
            checkPlayStoreUpdate();
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.dialog_error));
        builder.setMessage(getString(R.string.dialog_network_error));
        builder.setPositiveButton(getString(R.string.dialog_hide), new gq1(this, 3));
        AlertDialog create = builder.create();
        create.setCancelable(false);
        create.show();
    }

    private void GetServerList() {
        b32.j = new ArrayList();
        new pt0(this, (byte) 0).execute(new String[0]);
    }

    private void animarAberturaNeon() {
        try {
            TextView textView = (TextView) findViewById(R.id.splash_versao);
            if (textView != null) {
                textView.setText(s02.j("rOUkM2J9Gw==\n", "2tQKAkxMLnc=\n"));
            }
            View findViewById = findViewById(R.id.background_splash);
            if (findViewById != null) {
                findViewById.setScaleX(1.09f);
                findViewById.setScaleY(1.09f);
                findViewById.animate().scaleX(1.0f).scaleY(1.0f).setDuration(10000L).setInterpolator(new PathInterpolator(0.2f, 0.6f, 0.2f, 1.0f)).start();
            }
            View findViewById2 = findViewById(R.id.logo_area);
            if (findViewById2 != null) {
                findViewById2.setAlpha(0.0f);
                findViewById2.setScaleX(0.9f);
                findViewById2.setScaleY(0.9f);
                findViewById2.animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f).setDuration(800L).setInterpolator(new PathInterpolator(0.2f, 0.8f, 0.2f, 1.0f)).start();
            }
        } catch (Exception e) {
            Log.e(s02.j("l/xYYxHuXkOw5UJrFv8=\n", "xIw0AmKGHyA=\n"), s02.j("4jWgtREQtgqjP6j4ERGyF/cuu7k=\n", "g1vJ2HBz12U=\n"), e);
        }
    }

    private void checkAndRequestNotificationsPermission() {
        if (Build.VERSION.SDK_INT < 33) {
            u80.a();
        } else {
            if (checkSelfPermission(s02.j("A1vXqB/h1RgSUMG3GfvCXw1bnYo/2+VpLHrnkzbB8nc2fPyUIw==\n", "YjWz2nCIsTY=\n")) != 0) {
                requestPermissions(new String[]{s02.j("YqV5dvokpahzrm9p/D6y72ylM1TaHpXZTYRJTdMEgsdXglJKxg==\n", "A8sdBJVNwYY=\n")}, 3);
                return;
            }
            u80.a();
        }
        if (this.o) {
            CheckVersion();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkAndRequestPermissions() {
        if (checkSelfPermission(s02.j("l01cxEjAdkiGRkrbTtphD5lNFuRi6l00snx542PgXQ==\n", "9iM4tiepEmY=\n")) != 0) {
            ActivityCompat.requestPermissions(this, new String[]{s02.j("SzMI6H5Vq9xaOB73eE+8m0UzQshUf4CgbgItz1V1gA==\n", "Kl1smhE8z/I=\n")}, 1);
        } else {
            this.o = true;
            CheckVersion();
        }
    }

    private void checkPlayStoreUpdate() {
        eh2 eh2Var;
        synchronized (k92.class) {
            try {
                if (k92.f535a == null) {
                    Context applicationContext = getApplicationContext();
                    if (applicationContext == null) {
                        applicationContext = this;
                    }
                    k92.f535a = new eh2(new y00(applicationContext, 7));
                }
                eh2Var = k92.f535a;
            } catch (Throwable th) {
                throw th;
            }
        }
        v5 v5Var = (v5) eh2Var.m.b();
        this.r = v5Var;
        er2 a2 = ((od2) v5Var).a();
        bq1 bq1Var = new bq1(this, 1);
        a2.getClass();
        gi1 gi1Var = hw1.f408a;
        a2.b(gi1Var, bq1Var);
        a2.a(gi1Var, new bq1(this, 2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initializeGLSurfaceView(ConstraintLayout constraintLayout) {
        GLSurfaceView gLSurfaceView = new GLSurfaceView(this);
        this.p = gLSurfaceView;
        gLSurfaceView.setEGLContextClientVersion(2);
        this.p.setPreserveEGLContextOnPause(true);
        this.p.setRenderer(this);
        this.p.setRenderMode(1);
        this.p.setLayoutParams(new ConstraintLayout.LayoutParams(-1, -1));
        constraintLayout.addView(this.p);
        this.p.requestRender();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$CheckData$8(String str) {
        try {
            hw0 n = wh2.n(getApplicationContext(), wh2.m(str), jp.f509a);
            ArrayList arrayList = n.m;
            b32.i = new ArrayList(arrayList);
            if (arrayList.isEmpty() && n.n.isEmpty()) {
                b32.m = x22.m;
            } else {
                b32.m = x22.n;
                y22[] y22VarArr = y22.m;
            }
        } catch (IOException e) {
            e = e;
            e.printStackTrace();
            n81.a(getApplicationContext(), getString(R.string.error_json_process));
            b32.m = x22.o;
            GoToMain();
        } catch (RuntimeException e2) {
            e = e2;
            e.printStackTrace();
            n81.a(getApplicationContext(), getString(R.string.error_json_process));
            b32.m = x22.o;
            GoToMain();
        }
        GoToMain();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$CheckData$9(h42 h42Var) {
        h42Var.printStackTrace();
        n81.a(getApplicationContext(), getString(R.string.error_json_fetch));
        b32.m = x22.o;
        GoToMain();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$checkPlayStoreUpdate$1(u5 u5Var) {
        if (isFinishing() || isDestroyed()) {
            return;
        }
        if (u5Var.f1012a == 2) {
            byte b = (byte) (((byte) (0 | 1)) | 2);
            if (b != 3) {
                StringBuilder sb = new StringBuilder();
                if ((b & 1) == 0) {
                    sb.append(" appUpdateType");
                }
                if ((b & 2) == 0) {
                    sb.append(" allowAssetPackDeletion");
                }
                throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
            }
            PendingIntent pendingIntent = u5Var.b;
            if (pendingIntent == null) {
                pendingIntent = null;
            }
            if (pendingIntent != null) {
                s02.j("wTLoeAPEdZ7mK/JwBNU=\n", "kkKEGXCsNP0=\n");
                s02.j("+QF6FzjlpyV70szVO6y5LcsFYBiXIash1FVhF3TcsSXBVVwCO/64\n", "uHUPdlSM3UQ=\n");
                showPlayStoreUpdateDialog(u5Var);
                return;
            }
        }
        if (u5Var.f1012a == 3) {
            s02.j("XFwIGxpLrGB7RRITHVo=\n", "Dyxkemkj7QM=\n");
            s02.j("i/VQ0KxT3y8JJuYSrxrAI+rxV96nSMA9ue4=\n", "yoElscA6pU4=\n");
            startImmediateUpdate(u5Var);
        } else {
            s02.j("gWcNbFufqyymfhdkXI4=\n", "0hdhDSj36k8=\n");
            s02.j("/EHhZERGvY7DTfYl5pULTMAE4iUFYqSO1gTfMEpArcOPUuk2TFShjM5K6CsFeJug4Qqiag==\n", "rySMRCUyyO8=\n");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$checkPlayStoreUpdate$2(Exception exc) {
        if (isFinishing() || isDestroyed()) {
            return;
        }
        Log.e(s02.j("WVhUU8Mur0R+QU5bxD8=\n", "Cig4MrBG7ic=\n"), s02.j("tRPi7+fr4IGGBOLpoePswIJBwOym86/yhA7i5f2q\n", "8GGQgMeKj6E=\n") + exc.getMessage());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$onResume$0(u5 u5Var) {
        if (u5Var.f1012a == 3) {
            startImmediateUpdate(u5Var);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onSurfaceCreated$7(String str, String str2) {
        if (str.contains(s02.j("GOZROQQd//c60noFOz//4DDHfgIsKdPqMMRRAD8o1OA=\n", "X6oOcElaoIM=\n"))) {
            jp.f509a = s02.j("ZJu7\n", "FO3Jg3mp6ys=\n");
        } else if (str2.contains(s02.j("R+WzndrAsA==\n", "H4bf9Kqz1b8=\n")) || !(str.contains(s02.j("13CV+jMBaWf1RL7KGTBpcP9Rus0OJkV6/1KV2xMhBw==\n", "kDzKv2tVNhM=\n")) || str.contains(s02.j("KMFo7rAQQX4K9UPemiFBaQDgR9mNN21jAONo2NswfQ==\n", "b403q+hEHgo=\n")) || str.contains(s02.j("IFDVTcvcZOEIcfp+4+tI5wNDy1jFx0/nH2j/fuM=\n", "ZxyKDIaYO4I=\n")))) {
            jp.f509a = s02.j("WJ86\n", "PetZQgdPmhQ=\n");
        } else {
            jp.f509a = s02.j("yzyT\n", "r0TniroiZJE=\n");
        }
        SharedPreferences.Editor putString = getSharedPreferences(s02.j("Vs3i8DGnGIs=\n", "Mb2Xs1DEcO4=\n"), 0).edit().putString(s02.j("6qWZ\n", "jdXsH1tbVWM=\n"), jp.f509a);
        String j = s02.j("acGx5rGIvc0=\n", "G6TfgtT62L8=\n");
        if (str2 == null) {
            str2 = "";
        }
        putString.putString(j, str2).apply();
        GetServerList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showDialogSequence$3(AlertDialog alertDialog, int i, View view) {
        alertDialog.dismiss();
        showDialogSequence(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showDialogSequence$4(AlertDialog alertDialog, int i, View view) {
        alertDialog.dismiss();
        showDialogSequence(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showPlayStoreUpdateDialog$5(AlertDialog alertDialog, u5 u5Var, View view) {
        alertDialog.dismiss();
        startImmediateUpdate(u5Var);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showPlayStoreUpdateDialog$6(AlertDialog alertDialog, View view) {
        alertDialog.dismiss();
        Toast.makeText(this, R.string.update_required, 1).show();
        finishAffinity();
    }

    private void showDialogSequence(int i) {
        View findViewById;
        View findViewById2;
        int[] iArr = {R.layout.dialog_playstore_update, R.layout.dialog_update, R.layout.dialog_maintenance, R.layout.dialog_nickname_error, R.layout.dialog_server_password};
        String[] strArr = {s02.j("9XxPZAJ2y6CFCloXexnJqeR7OS1BLPrF\n", "xFN6RC9Wm+w=\n"), s02.j("yQc8DauzRsK/aV1optJBw65hX2LVszvEnlpkSOr7fLs=\n", "+ygJLYaTE5I=\n"), s02.j("DKo33HYFRY9x0Fa5FeaPDbzKItQXRHqvUe9j1Q==\n", "P4UC/FslCM4=\n"), s02.j("2eNWhMuq7Eyuhy3lq8+CQL+eLPbGovRgn6EGyI7lgmaBrRHLzw==\n", "7cxjpOaKogU=\n"), s02.j("sxPrvCZspYbUapvOKxy3kNVrkc5PbN6V406z+Wckmeo=\n", "hjzenAtM9sM=\n")};
        if (i >= 5) {
            Toast.makeText(this, s02.j("P61/HLJlBAMcqX1SpXBWBrpj0Z+5JQ==\n", "ecQSPNYEJGc=\n"), 0).show();
            return;
        }
        View view = null;
        View inflate = getLayoutInflater().inflate(iArr[i], (ViewGroup) null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.TransparentDialog);
        builder.setView(inflate);
        builder.setCancelable(false);
        final AlertDialog create = builder.create();
        if (create.getWindow() != null) {
            create.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }
        Toast.makeText(this, strArr[i], 0).show();
        if (i == 0) {
            view = inflate.findViewById(R.id.update_button);
            findViewById = inflate.findViewById(R.id.cancel_button);
        } else if (i != 1) {
            if (i == 2) {
                findViewById2 = inflate.findViewById(R.id.ok_button);
            } else if (i == 3) {
                findViewById2 = inflate.findViewById(R.id.ok_button);
            } else if (i != 4) {
                findViewById = null;
            } else {
                view = inflate.findViewById(R.id.connect_button);
                findViewById = inflate.findViewById(R.id.exit_button);
            }
            view = findViewById2;
            findViewById = null;
        } else {
            view = inflate.findViewById(R.id.update_button);
            findViewById = inflate.findViewById(R.id.cancel_button);
        }
        final int i2 = i + 1;
        if (view != null) {
            final int i3 = 0;
            view.setOnClickListener(new View.OnClickListener(this) { // from class: a.cq1
                public final /* synthetic */ SplashActivity n;

                {
                    this.n = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    switch (i3) {
                        case 0:
                            this.n.lambda$showDialogSequence$3(create, i2, view2);
                            return;
                        default:
                            this.n.lambda$showDialogSequence$4(create, i2, view2);
                            return;
                    }
                }
            });
        }
        if (findViewById != null) {
            final int i4 = 1;
            findViewById.setOnClickListener(new View.OnClickListener(this) { // from class: a.cq1
                public final /* synthetic */ SplashActivity n;

                {
                    this.n = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    switch (i4) {
                        case 0:
                            this.n.lambda$showDialogSequence$3(create, i2, view2);
                            return;
                        default:
                            this.n.lambda$showDialogSequence$4(create, i2, view2);
                            return;
                    }
                }
            });
        }
        create.show();
    }

    private void showPlayStoreUpdateDialog(u5 u5Var) {
        View inflate = getLayoutInflater().inflate(R.layout.dialog_playstore_update, (ViewGroup) null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.TransparentDialog);
        builder.setView(inflate);
        builder.setCancelable(false);
        AlertDialog create = builder.create();
        if (create.getWindow() != null) {
            create.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }
        inflate.findViewById(R.id.update_button).setOnClickListener(new dr0(this, create, u5Var, 4));
        inflate.findViewById(R.id.cancel_button).setOnClickListener(new u00(5, this, create));
        create.show();
    }

    private void showPlayStoreUpdateDialogSimulation() {
        showDialogSequence(0);
    }

    private void startImmediateUpdate(u5 u5Var) {
        Exception exc;
        try {
            v5 v5Var = this.r;
            byte b = (byte) (((byte) (0 | 1)) | 2);
            if (b != 3) {
                try {
                    StringBuilder sb = new StringBuilder();
                    if ((b & 1) == 0) {
                        sb.append(" appUpdateType");
                    }
                    if ((b & 2) == 0) {
                        sb.append(" allowAssetPackDeletion");
                    }
                    throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
                } catch (Exception e) {
                    exc = e;
                    Log.e(s02.j("mfwTnFVDFxO+5QmUUlI=\n", "yox//SYrVnA=\n"), s02.j("PpE169cKXL8SjS7nngpBvxqXMuWbAkn+uESEJ5hREw==\n", "e+NHhPdrM58=\n") + exc.getMessage());
                }
            }
            ((od2) v5Var).getClass();
            if (u5Var != null) {
                PendingIntent pendingIntent = u5Var.b;
                if ((pendingIntent != null ? pendingIntent : null) != null && !u5Var.c) {
                    u5Var.c = true;
                    if (pendingIntent == null) {
                        pendingIntent = null;
                    }
                    try {
                        startIntentSenderForResult(pendingIntent.getIntentSender(), 100, null, 0, 0, 0, null);
                    } catch (Exception e2) {
                        e = e2;
                        exc = e;
                        Log.e(s02.j("mfwTnFVDFxO+5QmUUlI=\n", "yox//SYrVnA=\n"), s02.j("PpE169cKXL8SjS7nngpBvxqXMuWbAkn+uESEJ5hREw==\n", "e+NHhPdrM58=\n") + exc.getMessage());
                    }
                }
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    public void CheckData(String str) {
        qe1 v = gt1.v(this);
        ct1 ct1Var = new ct1(str, new bq1(this, 3), new bq1(this, 4));
        ct1Var.v = new r0(AccessibilityNodeInfoCompat.EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_MAX_LENGTH, 2);
        v.a(ct1Var);
    }

    public void GetLinksList() {
        b32.k = new ArrayList();
        new pt0(this).execute(new String[0]);
    }

    public void GoToMain() {
        new Handler().postDelayed(new eq1(this, 1), 1200L);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 100 || i2 == -1) {
            return;
        }
        Log.e(s02.j("bnZgelgcDwVJb3pyXw0=\n", "PQYMGyt0TmY=\n"), s02.j("VjP2XREHux5iLfFZCQ7+GSMs4BwDA/IRZie8HCYO9A5qLfUcBBLrUw==\n", "A0OSPGVim30=\n"));
        Toast.makeText(this, R.string.update_required, 1).show();
        finishAffinity();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    @SuppressLint({"LongLogTag"})
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (DownloadService.q == null) {
            try {
                ((NotificationManager) getSystemService(s02.j("FQTe5hEpxbEPAsXh\n", "e2uqj3dAptA=\n"))).cancel(4211);
            } catch (Throwable unused) {
            }
        }
        FirebaseCrashlytics.getInstance().deleteUnsentReports();
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true);
        try {
            FirebaseCrashlytics firebaseCrashlytics = FirebaseCrashlytics.getInstance();
            firebaseCrashlytics.setCustomKey(s02.j("iToI6kn0fTmFKwg=\n", "6k97niaZIlQ=\n"), b32.c(this));
            firebaseCrashlytics.setCustomKey(s02.j("0BRnGMTd/5HSBlUL+tf/mQ==\n", "t3UKfZu7lv0=\n"), b32.g(this));
            ArrayList b = b32.b(this);
            firebaseCrashlytics.setCustomKey(s02.j("+S8F1LPCAUfyKRrDv94V\n", "lEZ2p9qsZhg=\n"), b.isEmpty() ? s02.j("4A==\n", "zba2l38Zr1c=\n") : String.join(s02.j("sw==\n", "n+eK8kTA0rc=\n"), b));
        } catch (Exception unused2) {
            s02.j("AApAr5Oky3InE1qnlLU=\n", "U3oszuDMihE=\n");
            s02.j("M45BKcudkdpVnUgmw86ExxSdDSTZyZHRGs9JIIrUnsYBjkEgydyflRuADQLY3IPdGZZZKMnO\n", "de8tQaq98LU=\n");
        }
        this.q = FirebaseAnalytics.getInstance(this);
        Bundle bundle2 = new Bundle();
        bundle2.putString(s02.j("4vF51zTFngLw/24=\n", "kZILslGrwWw=\n"), s02.j("CZO1WYug+PkZkbxdlg==\n", "euPZOPjIp4o=\n"));
        this.q.a(s02.j("GEHktiGg1rc=\n", "eTGU6U7Qs9k=\n"), bundle2);
        setContentView(R.layout.activity_splash);
        File file = new File(s02.j("5I8HaUkj/S/kmR5zVyPuL6/TQyl6LP44pJUXKV8j7ivknxxrFTH7J7uJHW9PO7QtqpEWKV0r9i+4\n0w==\n", "y/xzBjtCmko=\n"));
        File file2 = new File(file, s02.j("d1GepJGmdDNjSg==\n", "Gz75x/DSWkc=\n"));
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            xu0.u(file2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        animarAberturaNeon();
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true));
        if (Build.VERSION.SDK_INT >= 28) {
            getWindow().getAttributes().layoutInDisplayCutoutMode = 1;
        }
        getWindow().getDecorView().setSystemUiVisibility(7942);
        new Handler().postDelayed(new eq1(this, 0), 2000L);
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onDrawFrame(GL10 gl10) {
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        GLSurfaceView gLSurfaceView = this.p;
        if (gLSurfaceView != null) {
            gLSurfaceView.onPause();
        }
        super.onPause();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 1) {
            if (iArr.length > 0 && iArr[0] == 0) {
                this.o = true;
            }
            checkAndRequestNotificationsPermission();
            return;
        }
        if (i == 3) {
            if (iArr.length > 0 && iArr[0] == 0) {
                u80.a();
            }
            if (this.o) {
                CheckVersion();
            }
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        GLSurfaceView gLSurfaceView = this.p;
        if (gLSurfaceView != null) {
            gLSurfaceView.onResume();
        }
        v5 v5Var = this.r;
        if (v5Var != null) {
            er2 a2 = ((od2) v5Var).a();
            bq1 bq1Var = new bq1(this, 0);
            a2.getClass();
            a2.b(hw1.f408a, bq1Var);
        }
        super.onResume();
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        String glGetString = gl10.glGetString(7939);
        String glGetString2 = gl10.glGetString(7937);
        if (glGetString == null) {
            glGetString = "";
        }
        if (glGetString2 == null) {
            glGetString2 = "";
        }
        runOnUiThread(new or(this, glGetString, glGetString2, 8));
    }
}
