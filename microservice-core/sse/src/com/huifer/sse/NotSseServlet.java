package com.huifer.sse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-06-02
 */
@WebServlet("/notsse")
public class NotSseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        PrintWriter writer = resp.getWriter();


        for (int i = 0; i < 5; i++) {
            writer.print("data:" + i + "\n");
            writer.print("\n");
            writer.flush();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
