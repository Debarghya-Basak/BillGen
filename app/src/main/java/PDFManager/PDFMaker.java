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

import org.checkerframework.checker.units.qual.A;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

public class PDFMaker extends AppCompatActivity {

    public void generate(Context context) {

        PdfDocument pdfDocument = new PdfDocument();
        Paint myPaint = new Paint();

        int lineCounter = -580;
        int columnCounter = 10;
        int pageCount = 0;

        ArrayList<Canvas> canvasArrayList = new ArrayList<>();
        ArrayList<PdfDocument.Page> pagesArrayList = new ArrayList<>();

        //DRAW SECTION
        PdfDocument.PageInfo page = new PdfDocument.PageInfo.Builder(595,842,1).create();
        pagesArrayList.add(pdfDocument.startPage(page));
        canvasArrayList.add(pagesArrayList.get(pageCount).getCanvas());

        canvasArrayList.get(pageCount).rotate(90);
        canvasArrayList.get(pageCount).drawLine(168, -592, 168, 0, myPaint);
        canvasArrayList.get(pageCount).drawLine(168*2, -592, 168*2, 0, myPaint);
        canvasArrayList.get(pageCount).drawLine(168*3, -592, 168*3, 0, myPaint);
        canvasArrayList.get(pageCount).drawLine(168*4, -592, 168*4, 0, myPaint);

        canvasArrayList.get(pageCount).drawLine(0, -30, 842, -30, myPaint);

        myPaint.setTextSize(15);
        canvasArrayList.get(pageCount).drawText("12/09/2023 LNGH", columnCounter, lineCounter, myPaint); //TODO: Print Date and time of the bill

        for(Map<String,String> item : BillEditor.bill){

            if(lineCounter > -30){
                columnCounter+=168;
                lineCounter = -580;

                if(columnCounter > 168*5){
                    columnCounter = 10;

                    canvasArrayList.get(pageCount).rotate(270);
                    pdfDocument.finishPage(pagesArrayList.get(pageCount));

                    pageCount++;

                    PdfDocument.PageInfo pageNext = new PdfDocument.PageInfo.Builder(595,842,1).create();
                    pagesArrayList.add(pdfDocument.startPage(page));
                    canvasArrayList.add(pagesArrayList.get(pageCount).getCanvas());

                    canvasArrayList.get(pageCount).rotate(90);
                    canvasArrayList.get(pageCount).drawLine(168, -592, 168, 0, myPaint);
                    canvasArrayList.get(pageCount).drawLine(168*2, -592, 168*2, 0, myPaint);
                    canvasArrayList.get(pageCount).drawLine(168*3, -592, 168*3, 0, myPaint);
                    canvasArrayList.get(pageCount).drawLine(168*4, -592, 168*4, 0, myPaint);

                    canvasArrayList.get(pageCount).drawLine(0, -30, 842, -30, myPaint);
                }
            }

            if(item.get("Type").equals("Item") && lineCounter < -60){
                myPaint.setTextSize(15);
                lineCounter+=20;
                canvasArrayList.get(pageCount).drawText(item.get("Name"), columnCounter, lineCounter, myPaint);

            }
            else if(item.get("Type").equals("Item") && lineCounter >= -60){
                columnCounter+=168;
                lineCounter = -580;

                if(columnCounter > 168*5){
                    columnCounter = 10;

                    canvasArrayList.get(pageCount).rotate(270);
                    pdfDocument.finishPage(pagesArrayList.get(pageCount));

                    pageCount++;

                    PdfDocument.PageInfo pageNext = new PdfDocument.PageInfo.Builder(595,842,1).create();
                    pagesArrayList.add(pdfDocument.startPage(page));
                    canvasArrayList.add(pagesArrayList.get(pageCount).getCanvas());

                    canvasArrayList.get(pageCount).rotate(90);
                    canvasArrayList.get(pageCount).drawLine(168, -592, 168, 0, myPaint);
                    canvasArrayList.get(pageCount).drawLine(168*2, -592, 168*2, 0, myPaint);
                    canvasArrayList.get(pageCount).drawLine(168*3, -592, 168*3, 0, myPaint);
                    canvasArrayList.get(pageCount).drawLine(168*4, -592, 168*4, 0, myPaint);

                    canvasArrayList.get(pageCount).drawLine(0, -30, 842, -30, myPaint);
                }

                myPaint.setTextSize(15);
                canvasArrayList.get(pageCount).drawText(item.get("Name"), columnCounter, lineCounter, myPaint);
            }
            else{
                myPaint.setTextSize(10);
                lineCounter+=15;
//                canvas.get(pageCount).drawText(item.get("ColorOrSize") + "->" +
//                        item.get("Quantity") + item.get("Unit") + " * " +
//                        item.get("PricePerUnit") + "=" +
//                        Double.parseDouble(item.get("Quantity")) * Double.parseDouble(item.get("PricePerUnit")), 10, lineCounter, myPaint);

                String output = String.format("%-6s->%6s%2s * %-7s=%7s", item.get("ColorOrSize")
                        ,item.get("Quantity")
                        ,item.get("Unit")
                        ,item.get("PricePerUnit")
                        ,(int)Double.parseDouble(item.get("Quantity")) * (int)Double.parseDouble(item.get("PricePerUnit")) + "");
                Log.d("Debug", "Print testing : "+output);

                canvasArrayList.get(pageCount).drawText(output, columnCounter, lineCounter, myPaint);
            }
        }

        lineCounter+=25;
        myPaint.setTextSize(15);
        myPaint.setFakeBoldText(true);
        canvasArrayList.get(pageCount).drawText("TotalPrice : " + BillEditor.totalPrice, columnCounter, lineCounter, myPaint);

        canvasArrayList.get(pageCount).rotate(270);
        pdfDocument.finishPage(pagesArrayList.get(pageCount));
        //END OF DRAW SECTION

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
