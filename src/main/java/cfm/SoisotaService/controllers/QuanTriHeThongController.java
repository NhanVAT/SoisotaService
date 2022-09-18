package cfm.SoisotaService.controllers;

import cfm.SoisotaService.services.MenuService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/quanTriHeThong")
@Api(tags = "quanTriHeThong")
@RequiredArgsConstructor
public class QuanTriHeThongController {

  private final MenuService menuService;

}
