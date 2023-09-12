package PDFManager;

import static android.content.Context.STORAGE_SERVICE;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.graphics.pdf.PdfRenderer;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.dbtapps.billgen.Activities.BillEditor;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

public class PDFMaker extends AppCompatActivity {

    public void generate(Context context) {

        PdfDocument pdfDocument = new PdfDocument();
        Paint myPaint = new Paint();

        PdfDocument.PageInfo page1Info = new PdfDocument.PageInfo.Builder(595,842,1).create();
        PdfDocument.Page page1 = pdfDocument.startPage(page1Info);
        Canvas canvas = page1.getCanvas();

        //DRAW SECTION
        canvas.rotate(90);
        canvas.drawLine(168, -592, 168, 0, myPaint);
        canvas.drawLine(168*2, -592, 168*2, 0, myPaint);
        canvas.drawLine(168*3, -592, 168*3, 0, myPaint);
        canvas.drawLine(168*4, -592, 168*4, 0, myPaint);

        canvas.drawLine(0, -30, 842, -30, myPaint);

        int lineCounter = -580;
        int columnCounter = 10;

        myPaint.setTextSize(15);
        canvas.drawText("12/09/2023 LNGH", columnCounter, lineCounter, myPaint); //TODO: Print Date and time of the bill

        for(Map<String,String> item : BillEditor.bill){

            if(lineCounter > -30){
                columnCounter+=168;
                lineCounter = -580;
            }

            if(item.get("Type").equals("Item") && lineCounter < -60){
                myPaint.setTextSize(15);
                lineCounter+=20;
                canvas.drawText(item.get("Name"), columnCounter, lineCounter, myPaint);

            }
            else if(item.get("Type").equals("Item") && lineCounter >= -60){
                columnCounter+=168;
                lineCounter = -580;
                myPaint.setTextSize(15);
                canvas.drawText(item.get("Name"), columnCounter, lineCounter, myPaint);
            }
            else{
                myPaint.setTextSize(10);
                lineCounter+=15;
//                canvas.drawText(item.get("ColorOrSize") + "->" +
//                        item.get("Quantity") + item.get("Unit") + " * " +
//                        item.get("PricePerUnit") + "=" +
//                        Double.parseDouble(item.get("Quantity")) * Double.parseDouble(item.get("PricePerUnit")), 10, lineCounter, myPaint);

                String output = String.format("%-6s->%6s%2s * %-7s=%7s", item.get("ColorOrSize")
                        ,item.get("Quantity")
                        ,item.get("Unit")
                        ,item.get("PricePerUnit")
                        ,(int)Double.parseDouble(item.get("Quantity")) * (int)Double.parseDouble(item.get("PricePerUnit")) + "");
                Log.d("Debug", "Print testing : "+output);

                canvas.drawText(output, columnCounter, lineCounter, myPaint);
            }
        }

        lineCounter+=25;
        myPaint.setTextSize(15);
        myPaint.setFakeBoldText(true);
        canvas.drawText("TotalPrice : " + BillEditor.totalPrice, columnCounter, lineCounter, myPaint);

        canvas.rotate(270);
        //END OF DRAW SECTION


        pdfDocument.finishPage(page1);

        printToPdf(context, pdfDocument);
        pdfDocument.close();
    }

    private void printToPdf(Context context, PdfDocument pdfDocument) {

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


    }

}
