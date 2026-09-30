package com.proyecto.ventas_online.application.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class UpdateUserDTO {
    //Aquí lo mismo, podríamos implementar builder para solo llenar los campos que se
    //quieran actualizar
    private String name;
    private String lastname;
    private String email;
}
