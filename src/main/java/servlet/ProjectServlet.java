package servlet;
import servlet.Project;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class ProjectServlet extends HttpServlet {
    private SessionFactory factory;

    public void init() {
        factory = new Configuration()
                .configure()
                .addAnnotatedClass(Project.class)
                .buildSessionFactory();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        int marks = Integer.parseInt(req.getParameter("marks"));

        Project s = new Project();
        s.setId(id);
        s.setName(name);
        s.setMarks(marks);

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(s);
        tx.commit();
        session.close();

        res.getWriter().println("Student saved successfully!");
    }

    public void destroy() {
        factory.close();
    }
}


