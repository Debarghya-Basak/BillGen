package PDFManager;

import static android.content.Context.STORAGE_SERVICE;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;

public class PDFMaker extends AppCompatActivity {

    public void generate(Context context) {
        PdfDocument pdfDocument = new PdfDocument();
        Paint myPaint = new Paint();

        PdfDocument.PageInfo page1Info = new PdfDocument.PageInfo.Builder(595,842,1).create();
        PdfDocument.Page page1 = pdfDocument.startPage(page1Info);
        Canvas canvas = page1.getCanvas();
        myPaint.setTextSize(10);
        canvas.drawText("THE LNGH page 1", 10, 10, myPaint);
        pdfDocument.finishPage(page1);


        File file = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            StorageManager storageManager = (StorageManager) context.getSystemService(STORAGE_SERVICE);
            StorageVolume storageVolume = storageManager.getStorageVolumes().get(0);
            file = new File(storageVolume.getDirectory().getPath() + "/Download/Bill.pdf");
            Log.d("Debug", "PDFMaker : "+storageVolume.getDirectory().getPath()+ "/Download/Bill.pdf");
        }
        else{
            file = new File(Environment.getExternalStorageDirectory() + "/Download/bill.pdf");
        }

        try{
            pdfDocument.writeTo(new FileOutputStream(file));
        }catch (Exception e){
            Log.d("Debug", "PDFMaker : Failed to generate pdf. " + e.getLocalizedMessage());
        }

        pdfDocument.close();
    }

}
