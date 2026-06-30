package com.projetoextensao.lp2projetoextensaospring.dataTransfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginData {
    private String email;
    private String senha;
}
