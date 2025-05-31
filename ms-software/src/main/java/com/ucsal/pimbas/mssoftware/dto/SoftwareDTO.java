package com.ucsal.pimbas.mssoftware.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoftwareDTO {
    private Long id;
    private String name;
    private String linkInstall;
    private String version;
    private boolean softwareFree;
    private boolean available;
}