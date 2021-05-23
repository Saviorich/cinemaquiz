package by.bntu.fitr.cinemaquiz.controller;

import by.bntu.fitr.cinemaquiz.controller.command.Command;
import by.bntu.fitr.cinemaquiz.controller.command.CommandProvider;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet(name="Upload", value = "/Upload" )
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class FileUploadingServlet extends HttpServlet {


    private static final Logger logger = LogManager.getLogger(FileUploadingServlet.class);
    private static final CommandProvider provider = CommandProvider.getInstance();
    private static final String IMAGE_EDITOR_PART = "image_editor";
    private static final String IMAGES_DIRECTORY_NAME = "img";
    private static final String UPLOAD_DIR = "D:\\java_projects\\jwd_final_project\\cinemaquiz\\src\\main\\webapp\\"
            + IMAGES_DIRECTORY_NAME;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (ServletFileUpload.isMultipartContent(request)) {
            Part part = request.getPart(IMAGE_EDITOR_PART);
            String filename = part.getSubmittedFileName();
            String upload_path = UPLOAD_DIR + File.separator + filename;

            String imagePath = IMAGES_DIRECTORY_NAME + File.separator + filename;

            if (Files.exists(Paths.get(upload_path + filename).toAbsolutePath())) {
                imagePath = request.getParameter("image_path");
            }

            boolean isSuccess;
            try (InputStream inputStream = part.getInputStream()) {
                isSuccess = uploadFile(inputStream, upload_path);
            }
            if (isSuccess) {
                request.setAttribute("image_path", imagePath);
                process(request, response);
            }
        }
    }

    /**
     * Uploads image with stream
     *
     * @param inputStream stream taken from {@link Part}
     * @param path directory where image will be stored
     * @return true if file was successfully uploaded
     * @throws ServletException if IOException was thrown while uploading
     */
    private boolean uploadFile(InputStream inputStream, String path) throws ServletException {
        try {
            byte[] bytes = new byte[inputStream.available()];
            int result = inputStream.read(bytes);
            if (result != -1) {
                try (FileOutputStream fops = new FileOutputStream(path)) {
                    fops.write(bytes);
                }
            }
        } catch (IOException e) {
            logger.error(e);
            throw new ServletException(e);
        }
        return true;
    }

    /**
     * Process the request
     * @param request contains the command that will be executed
     */
    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name;
        Command command;

        name = request.getParameter("command");
        command = provider.takeCommand(name);

        command.execute(request, response);
    }
}
