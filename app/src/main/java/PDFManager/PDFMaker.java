package PDFManager;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import java.io.File;
import java.io.FileOutputStream;

public class PDFMaker {

    public void generate() {
        PdfDocument pdfDocument = new PdfDocument();
        Paint myPaint = new Paint();

        PdfDocument.PageInfo page1Info = new PdfDocument.PageInfo.Builder(250,400,1).create();
        PdfDocument.Page page1 = pdfDocument.startPage(page1Info);
        Canvas canvas = page1.getCanvas();
        canvas.drawText("THE LNGH page 1", 10, 10, myPaint);
        pdfDocument.finishPage(page1);

        File file = new File(Environment.getExternalStorageDirectory(), "/Bill.pdf");

        try{
            pdfDocument.writeTo(new FileOutputStream(file));
        }catch (Exception e){}

        pdfDocument.close();
    }

}
