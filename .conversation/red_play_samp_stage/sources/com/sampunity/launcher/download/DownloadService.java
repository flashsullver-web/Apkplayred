package com.sampunity.launcher.download;

import a.al1;
import a.b32;
import a.d1;
import a.fc1;
import a.fg0;
import a.g20;
import a.qq;
import a.s02;
import a.xe0;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.SystemClock;
import androidx.core.app.NotificationCompat;
import com.sampunity.game.R;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.Semaphore;

/* loaded from: classes2.dex */
public class DownloadService extends Service {
    public static final String o = s02.j("VrQXopd0VlJAtRP4nTtcQ1i+VOiLYlVOWroeoqdUdWFwlzve\n", "Ndt6jOQVOyI=\n");
    public static final String p = s02.j("reUYQnxdK9qW4ABLfw==\n", "yYpvLBAySr4=\n");
    public static volatile xe0 q;
    public static volatile qq r;
    public static volatile long s;
    public static volatile long t;
    public static volatile long u;
    public static volatile long v;
    public static volatile String w;
    public static PowerManager.WakeLock x;
    public static WifiManager.WifiLock y;
    public NotificationManager m;
    public long n;

    static {
        new ArrayList();
    }

    public static String c(long j) {
        return String.format(Locale.US, s02.j("dq+zmpBf7w==\n", "U4GC/LASrbI=\n"), Double.valueOf(j / 1048576.0d));
    }

    public static String d(long j, long j2) {
        return (j / 1048576) + s02.j("mA==\n", "twqBho0lCOk=\n") + (j2 / 1048576) + s02.j("Cflr\n", "KbQphwSu6S8=\n");
    }

    public final Notification a(long j, long j2, String str) {
        int i = j2 > 0 ? (int) ((100 * j) / j2) : 0;
        String string = s02.j("eM0fam1DLgB9xTV5ekMq\n", "GapqCx8nT24=\n").equals(str) ? getString(R.string.dl_aguardando_rede) : d(j, j2);
        PendingIntent service = PendingIntent.getService(this, 1, new Intent(this, (Class<?>) DownloadService.class).setAction(o), 201326592);
        NotificationCompat.Builder silent = new NotificationCompat.Builder(this, p).setSmallIcon(R.drawable.ic_notification).setContentTitle(getString(R.string.dl_notif_titulo)).setContentText(string).setProgress(100, i, j2 <= 0).setOngoing(true).setOnlyAlertOnce(true).setSilent(true);
        Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(getPackageName());
        if (launchIntentForPackage == null) {
            launchIntentForPackage = new Intent();
        }
        return silent.setContentIntent(PendingIntent.getActivity(this, 0, launchIntentForPackage, 201326592)).addAction(0, getString(R.string.dl_cancelar), service).build();
    }

    public final void b(String str) {
        e(false);
        try {
            NotificationManager notificationManager = this.m;
            NotificationCompat.Builder autoCancel = new NotificationCompat.Builder(this, p).setSmallIcon(R.drawable.ic_notification).setContentTitle(getString(R.string.dl_notif_titulo)).setContentText(str).setOnlyAlertOnce(true).setAutoCancel(true);
            Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(getPackageName());
            if (launchIntentForPackage == null) {
                launchIntentForPackage = new Intent();
            }
            notificationManager.notify(4211, autoCancel.setContentIntent(PendingIntent.getActivity(this, 0, launchIntentForPackage, 201326592)).build());
        } catch (Throwable unused) {
        }
        stopSelf();
    }

    public final void e(boolean z) {
        try {
            if (!z) {
                PowerManager.WakeLock wakeLock = x;
                if (wakeLock != null && wakeLock.isHeld()) {
                    x.release();
                }
                WifiManager.WifiLock wifiLock = y;
                if (wifiLock == null || !wifiLock.isHeld()) {
                    return;
                }
                y.release();
                return;
            }
            if (x == null) {
                PowerManager.WakeLock newWakeLock = ((PowerManager) getApplicationContext().getSystemService(s02.j("zo4NVdk=\n", "vuF6MKtZQws=\n"))).newWakeLock(1, s02.j("bpkNV/+g96RkwgRI/aDyv3yc\n", "HfhgJ4rOntA=\n"));
                x = newWakeLock;
                newWakeLock.setReferenceCounted(false);
            }
            if (y == null) {
                WifiManager.WifiLock createWifiLock = ((WifiManager) getApplicationContext().getSystemService(s02.j("zmEo0g==\n", "uQhOu6J94iI=\n"))).createWifiLock(Build.VERSION.SDK_INT >= 29 ? 3 : 1, s02.j("dHcv+p0zkTN+LCblnzOUKGZy\n", "BxZCiuhd+Ec=\n"));
                y = createWifiLock;
                createWifiLock.setReferenceCounted(false);
            }
            x.acquire(10800000L);
            y.acquire();
        } catch (Throwable unused) {
        }
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        this.m = (NotificationManager) getSystemService(s02.j("U1S5IDzzN6hJUqIn\n", "PTvNSVqaVMk=\n"));
        NotificationChannel notificationChannel = new NotificationChannel(p, getString(R.string.dl_canal), 2);
        notificationChannel.setShowBadge(false);
        this.m.createNotificationChannel(notificationChannel);
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i, int i2) {
        if (intent == null || !o.equals(intent.getAction())) {
            if (q == null) {
                ArrayList arrayList = b32.i == null ? new ArrayList() : new ArrayList(b32.i);
                try {
                    this.m.notify(4211, a(0L, 1L, null));
                } catch (Throwable unused) {
                }
                e(true);
                final xe0 xe0Var = new xe0(this, arrayList, new fg0(this, 14));
                q = xe0Var;
                xe0Var.p = SystemClock.elapsedRealtime();
                xe0Var.d.post(xe0Var.s);
                ArrayList arrayList2 = xe0Var.b;
                if (arrayList2.isEmpty()) {
                    xe0Var.d.post(new d1(xe0Var, 15));
                } else {
                    for (final int i3 = 0; i3 < arrayList2.size(); i3++) {
                        final g20 g20Var = (g20) arrayList2.get(i3);
                        if (g20Var.d >= 8388608) {
                            final int i4 = 0;
                            xe0Var.g.submit(new Runnable() { // from class: a.ve0
                                @Override // java.lang.Runnable
                                public final void run() {
                                    switch (i4) {
                                        case 0:
                                            int i5 = i3;
                                            g20 g20Var2 = g20Var;
                                            xe0 xe0Var2 = xe0Var;
                                            Semaphore semaphore = xe0Var2.i;
                                            try {
                                                semaphore.acquire();
                                                try {
                                                    xe0Var2.b(i5, g20Var2);
                                                    semaphore.release();
                                                } catch (Throwable th) {
                                                    semaphore.release();
                                                    throw th;
                                                }
                                            } catch (InterruptedException unused2) {
                                            }
                                            if (xe0Var2.m.incrementAndGet() >= xe0Var2.b.size()) {
                                                xe0Var2.d.post(new d1(xe0Var2, 15));
                                                return;
                                            }
                                            return;
                                        default:
                                            xe0 xe0Var3 = xe0Var;
                                            xe0Var3.b(i3, g20Var);
                                            if (xe0Var3.m.incrementAndGet() >= xe0Var3.b.size()) {
                                                xe0Var3.d.post(new d1(xe0Var3, 15));
                                                return;
                                            }
                                            return;
                                    }
                                }
                            });
                        } else {
                            final int i5 = 1;
                            xe0Var.f.submit(new Runnable() { // from class: a.ve0
                                @Override // java.lang.Runnable
                                public final void run() {
                                    switch (i5) {
                                        case 0:
                                            int i52 = i3;
                                            g20 g20Var2 = g20Var;
                                            xe0 xe0Var2 = xe0Var;
                                            Semaphore semaphore = xe0Var2.i;
                                            try {
                                                semaphore.acquire();
                                                try {
                                                    xe0Var2.b(i52, g20Var2);
                                                    semaphore.release();
                                                } catch (Throwable th) {
                                                    semaphore.release();
                                                    throw th;
                                                }
                                            } catch (InterruptedException unused2) {
                                            }
                                            if (xe0Var2.m.incrementAndGet() >= xe0Var2.b.size()) {
                                                xe0Var2.d.post(new d1(xe0Var2, 15));
                                                return;
                                            }
                                            return;
                                        default:
                                            xe0 xe0Var3 = xe0Var;
                                            xe0Var3.b(i3, g20Var);
                                            if (xe0Var3.m.incrementAndGet() >= xe0Var3.b.size()) {
                                                xe0Var3.d.post(new d1(xe0Var3, 15));
                                                return;
                                            }
                                            return;
                                    }
                                }
                            });
                        }
                    }
                }
            }
            return 2;
        }
        xe0 xe0Var2 = q;
        if (xe0Var2 != null) {
            xe0Var2.n = true;
            al1 al1Var = xe0Var2.e.m;
            synchronized (al1Var) {
                Iterator it = ((ArrayDeque) al1Var.q).iterator();
                if (it.hasNext()) {
                    if (it.next() == null) {
                        throw null;
                    }
                    throw new ClassCastException();
                }
                Iterator it2 = ((ArrayDeque) al1Var.o).iterator();
                if (it2.hasNext()) {
                    if (it2.next() == null) {
                        throw null;
                    }
                    throw new ClassCastException();
                }
                Iterator it3 = ((ArrayDeque) al1Var.p).iterator();
                while (it3.hasNext()) {
                    ((fc1) it3.next()).c();
                }
            }
            xe0Var2.f.shutdownNow();
            xe0Var2.g.shutdownNow();
            xe0Var2.h.shutdownNow();
            xe0Var2.d.removeCallbacks(xe0Var2.s);
        }
        b(getString(R.string.dl_cancelado));
        return 2;
    }
}
