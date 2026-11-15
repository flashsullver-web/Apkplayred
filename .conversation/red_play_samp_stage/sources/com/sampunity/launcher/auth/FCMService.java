package com.sampunity.launcher.auth;

import a.od1;
import a.s02;
import a.u80;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.sampunity.game.R;
import com.sampunity.launcher.activity.SplashActivity;
import java.util.Objects;

/* loaded from: classes2.dex */
public class FCMService extends FirebaseMessagingService {
    public static final String t = null;
    public static final String u = null;

    static {
        s02.j("94bsqXARNc7SoA==\n", "scWh+hVjQ6c=\n");
        t = s02.j("Se8dUP3gXtlD0R5P/OdRxFnvBEnn4EQ=\n", "Oo5wIIiON60=\n");
        u = s02.j("mKEkqWemboD3nGuefKlmkLYRoymgqnw=\n", "19IE6hXPD/M=\n");
    }

    public FCMService() {
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public final void c(od1 od1Var) {
        s02.j("sfvwT3plkATc7PtffmCcDZ2++lkhIg==\n", "/J6ePBsC9Wk=\n");
        od1Var.m.getString(TypedValues.TransitionType.S_FROM);
        if (od1Var.e().isEmpty() == true) goto L27;
        s02.j("BIpBsLyjzBtghkCxvOLPHy3RBQ==\n", "QOsl38+DqHo=\n");
        Objects.toString(od1Var.e());
        String str = (String) od1Var.e().get(s02.j("3GN2Q1s=\n", "qAoCLz4oTBM=\n"));
        String str2 = (String) od1Var.e().get(s02.j("DRzLeg==\n", "b3OvA1i5IE8=\n"));
        String str3 = (String) od1Var.e().get(s02.j("2X/JG/Vy\n", "uBy9cpocLaA=\n"));
        if (str == null) goto L8;
        if (str2 == null) goto L8;
        e(str, str2);
    L8:
        if (str3 == null) goto L27;
        int hashCode = str3.hashCode();
        if (hashCode == (-838846263)) goto L18;
        if (hashCode == 96891546) goto L15;
    L20:
        char c = 65535;
    L21:
        if (c == 0) goto L25;
        if (c == 1) goto L24;
        s02.j("kbMYKTnlMiO1A9yF9OJ3JLkU3tC6\n", "0HC/6pqKEkc=\n");
        goto L27
    L24:
        s02.j("6NtF7EY73K/nd5RAxTGQ6sdsjQ+LO8blxn+N\n", "qRjiL+VU5o8=\n");
        goto L27
    L25:
        s02.j("CfuWVLgdsTgJTET2dxvxeYuf8jR0Uu9xO0he+djf/X0k\n", "SDgxlxtyixg=\n");
        goto L27
    L15:
        if (str3.equals(s02.j("8Mo2XsQ=\n", "lbxTMLARQzc=\n")) == false) goto L20;
        c = 1;
        goto L21
    L18:
        if (str3.equals(s02.j("+Nk/3n9K\n", "jalbvwsvhvA=\n")) == false) goto L20;
        c = 0;
    L27:
        if (od1Var.f() == null) goto L30;
        String str4 = od1Var.f().b;
        String str5 = od1Var.f().c;
        s02.j("O7PodyWgPaK2e1+9LOlz4yEfMWo2pTH5VQ==\n", "ddycHkPJXsM=\n");
        s02.j("4SSdnk13Zbjt\n", "zQTe8T8HCoI=\n");
        e(str4, str5);
        return;
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public final void d(String str) {
        s02.j("sPIsKfNDh1ab83oAkHrSHQ==\n", "/p1aRtM36D0=\n");
        getSharedPreferences(u80.f1017a, 0).edit().putString(u80.b, str).apply();
        s02.j("jcjHrqcyyIyUh9+qpWTh77XIz6qlf+uhrcI=\n", "2aesy8kSjs8=\n");
    }

    public final void e(String str, String str2) {
        Intent intent = new Intent(this, SplashActivity.class);
        intent.addFlags(67108864);
        PendingIntent activity = PendingIntent.getActivity(this, 0, intent, 1140850688);
        Uri defaultUri = RingtoneManager.getDefaultUri(2);
        String str3 = t;
        NotificationCompat.Builder smallIcon = new NotificationCompat.Builder(this, str3).setSmallIcon(R.drawable.ic_notification);
        if (str != null) goto L6;
        str = s02.j("MwLD8mblIVo=\n", "fHHjsRSMQCk=\n");
    L6:
        NotificationCompat.Builder contentTitle = smallIcon.setContentTitle(str);
        if (str2 != null) goto L10;
        str2 = "";
    L10:
        NotificationCompat.Builder contentIntent = contentTitle.setContentText(str2).setAutoCancel(true).setSound(defaultUri).setPriority(1).setContentIntent(activity);
        NotificationManager notificationManager = (NotificationManager) getSystemService(s02.j("VHA2Ft6Z50JOdi0R\n", "Oh9Cf7jwhCM=\n"));
        NotificationChannel notificationChannel = new NotificationChannel(str3, u, 4);
        notificationChannel.setDescription(s02.j("RpNh9sIrs2fLW9YqwTHwYmfcf/DDLfBJe9xW7c0jow==\n", "CPwVn6RC0AY=\n"));
        notificationChannel.enableVibration(true);
        notificationChannel.enableLights(true);
        notificationManager.createNotificationChannel(notificationChannel);
        notificationManager.notify((int) System.currentTimeMillis(), contentIntent.build());
    }
}
