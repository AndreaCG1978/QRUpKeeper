package com.boxico.android.kn.qrupkeeper.manager.qr;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QRManager {

    private ZXingScannerView mScannerView;

    public ZXingScannerView getScanner() {
        return mScannerView;
    }

    public void setScanner(ZXingScannerView mScannerView) {
        this.mScannerView = mScannerView;
    }
}
