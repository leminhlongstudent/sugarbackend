package site.sugarnest.backend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import site.sugarnest.backend.dto.dto.ApiResponse;
import site.sugarnest.backend.dto.request.RoleRequest;
import site.sugarnest.backend.dto.response.RoleResponse;
import site.sugarnest.backend.service.Athorization.RoleService;

import java.util.List;

@RestController
@RequestMapping("/roles")
@AllArgsConstructor
public class RoleController {
    RoleService roleService;

    @PostMapping
    ApiResponse<RoleResponse> createRole(@RequestBody RoleRequest request) {
        return ApiResponse.<RoleResponse>builder()
                .message("Permission created!")
                .result(roleService.create(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<RoleResponse>> getAllRole() {
        return ApiResponse.<List<RoleResponse>>builder()
                .message("All permissions!")
                .result(roleService.getAll())
                .build();
    }

    @DeleteMapping("/{name}")
    ApiResponse<Void> deletePermission(@PathVariable String name) {
        roleService.delete(name);
        return ApiResponse.<Void>builder()
                .message("Permission deleted!")
                .build();
    }
}
