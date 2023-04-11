package br.com.via.avaliation.viaavaliation.dto;

import java.time.LocalDateTime;

public record BranchDTO(Long id,
                        String name,
                        String cnpj,
                        String city,
                        String uf,
                        String type,
                        Boolean active,
                        String created_at,
                        String updated_at) {}