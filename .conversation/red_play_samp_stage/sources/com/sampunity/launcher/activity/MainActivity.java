package com.sampunity.launcher.activity;

import a.b32;
import a.c5;
import a.cj1;
import a.ck2;
import a.cn1;
import a.dq;
import a.ee;
import a.ef;
import a.er2;
import a.f10;
import a.fg2;
import a.gw1;
import a.i5;
import a.ie1;
import a.j51;
import a.je1;
import a.jp;
import a.k72;
import a.ky;
import a.n20;
import a.n81;
import a.ne1;
import a.ou1;
import a.p22;
import a.qc2;
import a.s02;
import a.tg0;
import a.u00;
import a.un;
import a.uv0;
import a.v00;
import a.vv0;
import a.wh2;
import a.x5;
import a.xq2;
import a.zq0;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import com.bumptech.glide.a;
import com.google.android.gms.common.api.Status;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.sampunity.game.R;
import com.sampunity.launcher.model.LinksModel;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class MainActivity extends AppCompatActivity {
    public static final String q = null;
    public static final String r = null;
    public static final String s = null;
    public NavController m;
    public FirebaseAnalytics n;
    public boolean o;
    public int p;

    static {
        q = s02.j("Sd2bfUeA0uVn2IVGUpc=\n", "CK3rMjflvKY=\n");
        r = s02.j("U6Vozk+A2d5SoQ==\n", "PNUNoBDjtqs=\n");
        s = s02.j("Z7oOKkfkZ7F9sA8t\n", "Fd94QyKTOMI=\n");
    }

    public MainActivity() {
        this.o = false;
        this.p = 0;
    }

    private void aplicarTelaNeon(int i) {
        if (i != R.id.serversFragment) goto L5;
        int i2 = R.drawable.bg_servidores;
    L8:
        ImageView imageView = (ImageView) findViewById(R.id.background);
        if (imageView != null) goto L11;
    L13:
        ImageView imageView2 = (ImageView) findViewById(R.id.btn_home);
        ImageView imageView3 = (ImageView) findViewById(R.id.btn_settings);
        boolean z = true;
        int i3 = 0;
        if (i != R.id.mainFragment) goto L16;
        boolean z2 = true;
    L17:
        if (i == R.id.settingsFragment) goto L20;
        z = false;
    L20:
        int i4 = -1627389953;
        if (imageView2 == null) goto L30;
        if (z2 == false) goto L24;
        int i5 = R.drawable.nv_nav_ativo;
    L25:
        imageView2.setBackgroundResource(i5);
        if (z2 == false) goto L28;
        int i6 = -1;
    L29:
        imageView2.setColorFilter(i6);
        goto L30
    L28:
        i6 = -1627389953;
        goto L29
    L24:
        i5 = 0;
    L30:
        if (imageView3 == null) goto L38;
        if (z == false) goto L33;
        i3 = R.drawable.nv_nav_ativo;
    L33:
        imageView3.setBackgroundResource(i3);
        if (z == false) goto L36;
        i4 = -1;
    L36:
        imageView3.setColorFilter(i4);
        return;
    L38:
        return;
    L16:
        z2 = false;
        goto L17
    L11:
        if (i2 == this.p) goto L13;
        this.p = i2;
        imageView.setImageResource(i2);
        imageView.setAlpha(0.6f);
        imageView.animate().alpha(1.0f).setDuration(220).start();
        goto L13
    L5:
        if (i != R.id.settingsFragment) goto L7;
        i2 = R.drawable.bg_config;
        goto L8
    L7:
        i2 = R.drawable.bg_inicio;
        goto L8
    }

    private void checkAndRequestReview() {
        SharedPreferences sharedPreferences = getSharedPreferences(q, 0);
        String str = s;
        if (sharedPreferences.getBoolean(str, false) == true) goto L10;
        String str2 = r;
        int i = sharedPreferences.getInt(str2, 0) + 1;
        sharedPreferences.edit().putInt(str2, i).apply();
        s02.j("fCStZDIqZLxHLLBz\n", "MUXECnNJENU=\n");
        s02.j("OYTxqhd7vdIMm6E=\n", "ePSBinYZ2KA=\n");
        s02.j("Cp17uLS2\n", "KusewtHFQlQ=\n");
        if (i < 5) goto L9;
        this.o = true;
        sharedPreferences.edit().putBoolean(str, true).apply();
        return;
    L9:
        return;
    }

    public static /* synthetic */ void h(MainActivity mainActivity, View view) {
        mainActivity.lambda$initializeViews$3(view);
    }

    public static /* synthetic */ void i(MainActivity mainActivity, View view) {
        mainActivity.lambda$initializeViews$4(view);
    }

    private void initializeViews() {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment == null) goto L5;
        NavController navController = navHostFragment.getNavController();
        this.m = navController;
        navController.addOnDestinationChangedListener(new uv0(this));
    L5:
        findViewById(R.id.btn_home).setOnClickListener(new vv0(this, 0));
        findViewById(R.id.btn_servers).setOnClickListener(new vv0(this, 1));
        findViewById(R.id.btn_settings).setOnClickListener(new vv0(this, 2));
    }

    public static /* synthetic */ void j(MainActivity mainActivity, NavController navController, NavDestination navDestination, Bundle bundle) {
        mainActivity.lambda$initializeViews$2(navController, navDestination, bundle);
    }

    public static /* synthetic */ void k(MainActivity mainActivity, v00 v00Var, View view) {
        mainActivity.lambda$showBanner$1(v00Var, view);
    }

    public static /* synthetic */ void l(MainActivity mainActivity, View view) {
        mainActivity.lambda$initializeViews$5(view);
    }

    private /* synthetic */ void lambda$initializeViews$2(NavController navController, NavDestination navDestination, Bundle bundle) {
        if (navDestination.getId() != R.id.downloadFragment) goto L5;
        setBottomNavVisibility(8);
    L6:
        aplicarTelaNeon(navDestination.getId());
        return;
    L5:
        setBottomNavVisibility(0);
        goto L6
    }

    private /* synthetic */ void lambda$initializeViews$3(View view) {
        NavController navController = this.m;
        if (navController == null) goto L6;
        navController.navigate(R.id.mainFragment);
        return;
    }

    private /* synthetic */ void lambda$initializeViews$4(View view) {
        NavController navController = this.m;
        if (navController == null) goto L6;
        navController.navigate(R.id.serversFragment);
        return;
    }

    private /* synthetic */ void lambda$initializeViews$5(View view) {
        NavController navController = this.m;
        if (navController == null) goto L6;
        navController.navigate(R.id.settingsFragment);
        return;
    }

    private /* synthetic */ void lambda$showBanner$0(View view) {
        openBannerLink(b32.h);
    }

    private void lambda$showBanner$1(v00 v00Var, View view) {
        v00Var.f1058a.dismiss();
        if (this.o == false) goto L6;
        this.o = false;
        requestReview();
        return;
    }

    public static /* synthetic */ void m(MainActivity mainActivity, View view) {
        mainActivity.lambda$showBanner$0(view);
    }

    private void openBannerLink(String str) {
        Bundle bundle = new Bundle();     // Catch: Exception -> L4
        bundle.putString(s02.j("NHXM5hpMXfQkeA==\n", "VhSiiH8+AoE=\n"), str);     // Catch: Exception -> L4
        this.n.a(s02.j("ye0xa8xF+9vH5Txu\n", "q4xfBak3pLg=\n"), bundle);     // Catch: Exception -> L4
        startActivity(new Intent(s02.j("Jfo9k9QE+3At+i2E1RmxPyfgMI7VQ8kXAcM=\n", "RJRZ4bttn14=\n"), Uri.parse(str)));     // Catch: Exception -> L4
        return;
    }

    private void requestReview() {
        Context applicationContext = getApplicationContext();
        if (applicationContext != null) goto L6;
        applicationContext = this;
    L6:
        ou1 ou1Var = new ou1(new fg2(applicationContext));
        fg2 fg2Var = (fg2) ou1Var.n;
        Object[] objArr = {fg2Var.b};
        dq dqVar = fg2.c;
        dqVar.e("requestInAppReview (%s)", objArr);
        xq2 xq2Var = fg2Var.f277a;
        if (xq2Var != null) goto L16;
        Object[] objArr2 = new Object[0];
        if (Log.isLoggable("PlayCore", 6) == false) goto L11;
        Log.e("PlayCore", dq.g(dqVar.n, "Play Store app is either not installed or not the official version", objArr2));
    L11:
        Locale locale = Locale.getDefault();
        HashMap hashMap = k72.f532a;
        if (hashMap.containsKey(-1) == true) goto L14;
        String str = "";
    L15:
        er2 er2Var = un.z(new i5(new Status(-1, String.format(locale, "Review Error(%d): %s", new Object[]{-1, str}), null, null)));
    L17:
        er2Var.h(new tg0(3, this, ou1Var));
        return;
    L14:
        str = ((String) hashMap.get(-1)) + " (https://developer.android.com/reference/com/google/android/play/core/review/model/ReviewErrorCode.html#" + ((String) k72.b.get(-1)) + ")";
        goto L15
    L16:
        gw1 gw1Var = new gw1();
        xq2Var.a().post(new ck2(xq2Var, gw1Var, gw1Var, new qc2(fg2Var, gw1Var, gw1Var)));
        er2Var = gw1Var.f356a;
        goto L17
    }

    private void setBottomNavVisibility(int i) {
        findViewById(R.id.nav_bg).setVisibility(i);
        findViewById(R.id.veu_nav).setVisibility(i);
        findViewById(R.id.btn_home).setVisibility(i);
        findViewById(R.id.btn_servers).setVisibility(i);
        findViewById(R.id.btn_settings).setVisibility(i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v1, types: [a.oj, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v5, types: [a.hf, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v6, types: [a.hf, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v7, types: [a.hf, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v8, types: [a.hf, java.lang.Object] */
    private void showBanner(String str) {
        int i = 3;
        v00 v00Var = new v00(this);
        vv0 vv0Var = new vv0(this, i);
        ImageView imageView = v00Var.c;
        imageView.setOnClickListener(vv0Var);
        ne1 c = a.a(this).q.c(this);
        Uri parse = Uri.parse(str);
        c.getClass();
        je1 je1Var = new je1(c.m, c, Drawable.class, c.n);
        je1 y = je1Var.y(parse);
        if (parse != null) goto L5;
    L21:
        je1 je1Var2 = (je1) ((je1) y.d(f10.d)).n(false);
        je1Var2.getClass();
        n20 n20Var = n20.d;
        je1 je1Var3 = (je1) je1Var2.p(new Object());
        je1Var3.getClass();
        p22.a();
        if (ee.f(je1Var3.m, 2048) == false) goto L24;
    L32:
        ee eeVar = je1Var3;
    L33:
        je1Var3.F.c.getClass();
        Class cls = je1Var3.E;
        if (Bitmap.class.equals(cls) == false) goto L37;
        ef efVar = new ef(imageView, 0);
    L39:
        je1Var3.x(efVar, eeVar);
        v00Var.f1058a.show();
        v00Var.b.setOnClickListener(new u00(i, this, v00Var));
        return;
    L37:
        if (Drawable.class.isAssignableFrom(cls) == false) goto L42;
        efVar = new ef(imageView, 1);
        goto L39
    L42:
        throw new IllegalArgumentException("Unhandled class: " + cls + ", try .as*(Class).transcode(ResourceTranscoder)");
    L24:
        if (imageView.getScaleType() == null) goto L32;
        switch(ie1.f439a[imageView.getScaleType().ordinal()]) {
            case 1: goto L31;
            case 2: goto L30;
            case 3: goto L29;
            case 4: goto L29;
            case 5: goto L29;
            case 6: goto L28;
            default: goto L32;
        };
    L28:
        eeVar = je1Var3.w().g(n20.c, new Object());
        eeVar.A = true;
        goto L33
    L29:
        eeVar = je1Var3.w().g(n20.b, new Object());
        eeVar.A = true;
        goto L33
    L30:
        eeVar = je1Var3.w().g(n20.c, new Object());
        eeVar.A = true;
        goto L33
    L31:
        eeVar = je1Var3.w().g(n20Var, new Object());
        goto L33
    L5:
        if ("android.resource".equals(parse.getScheme()) == false) goto L21;
        Context context = je1Var.C;
        je1 je1Var4 = (je1) y.o(context.getTheme());
        ConcurrentHashMap concurrentHashMap = x5.f1166a;
        String packageName = context.getPackageName();
        ConcurrentHashMap concurrentHashMap2 = x5.f1166a;
        zq0 zq0Var = (zq0) concurrentHashMap2.get(packageName);
        if (zq0Var == null) goto L43;
    L20:
        y = (je1) je1Var4.m(new c5(context.getResources().getConfiguration().uiMode & 48, zq0Var));
        goto L21
    L43:
        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);     // Catch: PackageManager.NameNotFoundException -> L11
    L13:
        if (packageInfo == null) goto L15;
        String uuid = String.valueOf(packageInfo.versionCode);
    L16:
        j51 j51Var = new j51(uuid);
        zq0 zq0Var2 = (zq0) concurrentHashMap2.putIfAbsent(packageName, j51Var);
        if (zq0Var2 != null) goto L19;
        zq0Var = j51Var;
        goto L20
    L19:
        zq0Var = zq0Var2;
        goto L20
    L15:
        uuid = UUID.randomUUID().toString();
    L11:
        e = move-exception;
        Log.e("AppVersionSignature", "Cannot resolve info for" + context.getPackageName(), e);
        packageInfo = null;
        goto L13
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    @SuppressLint({"MissingInflatedId"})
    public void onCreate(Bundle bundle) {
        if (bundle != null) goto L5;
    L7:
        boolean z = false;
    L8:
        if (z == false) goto L10;
        bundle = null;
    L10:
        super.onCreate(bundle);
        if (z == false) goto L14;
        Intent intent = new Intent(this, SplashActivity.class);
        intent.addFlags(268468224);
        startActivity(intent);
        finish();
        return;
    L14:
        Intent intent2 = getIntent();
        if (intent2 != null) goto L17;
        String str = cj1.f134a;
        boolean equals = false;
    L18:
        if (equals == true) goto L21;
        Log.e(s02.j("vDBc71VQU02HOEH4\n", "8VE1gRQzJyQ=\n"), s02.j("RVYHHsPqg9/Hlg1N0fDX3nZcGAzU6oOcJHwMGdXr15FtWxSuEenK1WsU\n", "BDVibbCFo7E=\n"));
        Toast.makeText(this, R.string.unauthorized_access, 1).show();
        finishAffinity();
        return;
    L21:
        setContentView(R.layout.activity_main);
        this.n = FirebaseAnalytics.getInstance(this);
        Bundle bundle2 = new Bundle();
        bundle2.putString(s02.j("h9WBQuqHt+SV25Y=\n", "9LbzJ4/p6Io=\n"), s02.j("qGXy7wkTQLugYfU=\n", "xQSbgVZgI8k=\n"));
        bundle2.putString(s02.j("ro9VBbrovHaxjVQT\n", "3ewnYN+G4xU=\n"), s02.j("nJmo2cLsKJinkbXO\n", "0fjBt4OPXPE=\n"));
        this.n.a(s02.j("aZDm+FQS+iRzluM=\n", "GvOUnTF8pVI=\n"), bundle2);
        getSupportFragmentManager();
        String[][] strArr = cn1.b;
        String c = cn1.c(this);     // Catch: Exception -> L28
        if (c.isEmpty() == true) goto L68;
        File file = new File(c.concat(s02.j("Bwaib90=\n", "VEfvP/KWr5Y=\n")));     // Catch: Exception -> L28
        File file2 = new File(file, s02.j("RO7d3nFh8iIZ4drFdg==\n", "N4upqhgPlVE=\n"));     // Catch: Exception -> L28
        n81 c2 = n81.c(this);     // Catch: Exception -> L28
        if (file2.exists() == false) goto L30;
        cn1.b(file2, c2);     // Catch: Exception -> L28
        goto L68
    L30:
        int length = strArr.length;     // Catch: Exception -> L28
        int i = 0;
    L31:
        if (i >= length) goto L36;
        String[] strArr2 = strArr[i];     // Catch: Exception -> L28
        if (c2.f678a.contains(cn1.d(strArr2[0], strArr2[1])) == true) goto L34;
        i = i + 1;
        goto L31
    L34:
        boolean z2 = true;
    L37:
        String[][] strArr3 = cn1.c;
        if (z2 == true) goto L45;
        int length2 = strArr3.length;     // Catch: Exception -> L28
        int i2 = 0;
    L40:
        if (i2 >= length2) goto L45;
        String[] strArr4 = strArr3[i2];     // Catch: Exception -> L28
        if (c2.f678a.contains(cn1.d(strArr4[0], strArr4[1])) == true) goto L43;
        i2 = i2 + 1;     // Catch: Exception -> L28
        goto L40
    L43:
        z2 = true;
    L45:
        if (z2 == false) goto L68;
        if (file.exists() == true) goto L51;
        file.mkdirs();     // Catch: Exception -> L28
    L51:
        if (file2.exists() == false) goto L53;
        file2.delete();     // Catch: Exception -> L28
    L53:
        JSONObject a2 = cn1.a();     // Catch: Exception -> L28
        JSONObject jSONObject = a2.getJSONObject(s02.j("wL8eZQ5A\n", "o9N3AGA0LLs=\n"));     // Catch: Exception -> L28
        int length3 = strArr.length;     // Catch: Exception -> L28
        int i3 = 0;
    L54:
        if (i3 >= length3) goto L59;
        String[] strArr5 = strArr[i3];     // Catch: Exception -> L28
        String d = cn1.d(strArr5[0], strArr5[1]);     // Catch: Exception -> L28
        if (c2.f678a.contains(d) == false) goto L58;
        jSONObject.getJSONObject(strArr5[0]).put(strArr5[1], c2.f678a.getBoolean(d, false));     // Catch: Exception -> L28
    L58:
        i3 = i3 + 1;     // Catch: Exception -> L28
        goto L54
    L59:
        int length4 = strArr3.length;     // Catch: Exception -> L28
        int i4 = 0;
    L60:
        if (i4 >= length4) goto L65;
        String[] strArr6 = strArr3[i4];     // Catch: Exception -> L28
        String d2 = cn1.d(strArr6[0], strArr6[1]);     // Catch: Exception -> L28
        if (c2.f678a.contains(d2) == false) goto L64;
        jSONObject.getJSONObject(strArr6[0]).put(strArr6[1], c2.f678a.getInt(d2, -1));     // Catch: Exception -> L28
    L64:
        i4 = i4 + 1;     // Catch: Exception -> L28
        goto L60
    L65:
        file2.createNewFile();     // Catch: Exception -> L28
        FileOutputStream fileOutputStream = new FileOutputStream(file2);     // Catch: Exception -> L28
        fileOutputStream.write(a2.toString(2).getBytes());     // Catch: Exception -> L28
        fileOutputStream.close();     // Catch: Exception -> L28
        s02.j("ciUi6nxbjpABMjPtYVqbhkVgMOx6WMmwSSEk+3Flm4ZHJST7e1aMkA==\n", "IUBWnhU16eM=\n");     // Catch: Exception -> L28
        goto L68
    L36:
        z2 = false;
    L68:
        checkAndRequestReview();
        ArrayList arrayList = b32.k;
        if (arrayList == null) goto L76;
        if (arrayList.isEmpty() == true) goto L76;
        LinksModel linksModel = (LinksModel) b32.k.get(0);
        if (linksModel.getIsBanner() == false) goto L76;
        showBanner(linksModel.getBanner());
    L78:
        initializeViews();
        if (Build.VERSION.SDK_INT < 28) goto L81;
        ky.w(getWindow().getAttributes());
    L81:
        getWindow().getDecorView().setSystemUiVisibility(7942);
        return;
    L76:
        if (this.o == false) goto L78;
        this.o = false;
        requestReview();
    L28:
        e = move-exception;
        Log.e(cn1.f140a, s02.j("yD4lAqwp5+f+ODgft2fyov4pIxm3Z/Lx\n", "jUxXbd4JlYI=\n"), e);
        goto L68
    L17:
        equals = cj1.b.equals(intent2.getStringExtra(cj1.f134a));
        goto L18
    L5:
        if (wh2.h(jp.f509a) == true) goto L7;
        z = true;
        goto L8
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    public void setString(String str, String str2) {
        SharedPreferences.Editor edit = getSharedPreferences(s02.j("UnqnV6M=\n", "ESjuFvAL4JI=\n"), 0).edit();
        edit.putString(str, str2);
        edit.apply();
    }
}
