package vn.codegym.songapp.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.codegym.songapp.config.WebConfig;
import vn.codegym.songapp.models.Song;
import vn.codegym.songapp.service.SongService;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/song")
public class SongController {

    @Autowired
    private SongService songService;

    @GetMapping("")
    public String List(Model model, @ModelAttribute("message") String message) {
        model.addAttribute("songs", songService.findAll());

        return "song/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("song", new Song());
        return "song/form";
    }

    @PostMapping("/new")
    public String doCreate(
            @Valid @ModelAttribute("song") Song song,
            BindingResult result,
            @RequestParam("uploadFile") MultipartFile file,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "song/form";
        }

        if (file.isEmpty()) {
            result.rejectValue("filePath", "error.filePath", "Vui lòng chọn file bài hát!");
            return "song/form";
        }

        try {
            String filePath = handleFileUpload(file, null);
            if (filePath == null) {
                redirectAttributes.addFlashAttribute("message", "File không hợp lệ!");
                return "redirect:/song/new";
            }

            song.setFilePath(filePath);
            songService.create(song);

            redirectAttributes.addFlashAttribute("message", "Upload bài hát thành công!");
            return "redirect:/song";

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "Lỗi upload file: " + e.getMessage());
            return "redirect:/song/new";
        }
    }


    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        Song song = songService.getById(id);
        model.addAttribute("song", song);
        return "song/update";
    }

    @PostMapping("/update")
    public String doUpdate(
            @Valid @ModelAttribute("song") Song song,
            BindingResult result,
            @RequestParam("uploadFile") MultipartFile file,
            RedirectAttributes redirectAttributes) {

        try {
            Song existingSong = songService.getById(song.getId());

            String filePath = existingSong.getFilePath();
            if (result.hasErrors()) {
                song.setFilePath(existingSong.getFilePath());
                return "song/update";
            }
            if (!file.isEmpty()) {
                filePath = handleFileUpload(file, existingSong.getFilePath());
                if (filePath == null) {
                    redirectAttributes.addFlashAttribute("message", "File không hợp lệ!");
                    return "redirect:/song/update/" + song.getId();
                }
            }

            song.setFilePath(filePath);
            songService.update(song);

            redirectAttributes.addFlashAttribute("message", "Cập nhật bài hát thành công!");
            return "redirect:/song";

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "Lỗi khi cập nhật: " + e.getMessage());
            return "redirect:/song/update/" + song.getId();
        }
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id,
                         RedirectAttributes redirectAttributes) {
        songService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Xóa bài hát thành công!");
        return "redirect:/song";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        Song song = songService.getById(id);
        model.addAttribute("song", song);
        return "song/detail"; // Tên view
    }

    private String handleFileUpload(MultipartFile file, String oldFilePath) throws IOException {
        if (file == null || file.isEmpty()) return null;

        String originalName = file.getOriginalFilename();
        if (originalName == null || originalName.isBlank()) return null;

        String lowerName = originalName.toLowerCase();
        if (!(lowerName.endsWith(".mp3") || lowerName.endsWith(".wav")
                || lowerName.endsWith(".ogg") || lowerName.endsWith(".m4p"))) {
            return null;
        }

        if (oldFilePath != null) {
            File oldFile = new File(WebConfig.SONG_FOLDER + oldFilePath.replace("/songs/", ""));
            if (oldFile.exists()) oldFile.delete();
        }

        File uploadDir = new File(WebConfig.SONG_FOLDER);
        if (!uploadDir.exists()) uploadDir.mkdirs();

        String newFilename = System.currentTimeMillis() + "_" + originalName;
        File destination = new File(uploadDir, newFilename);
        file.transferTo(destination);

        return "/songs/" + newFilename;
    }
}
