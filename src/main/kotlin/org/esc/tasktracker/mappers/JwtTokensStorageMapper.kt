package org.esc.tasktracker.mappers

import org.esc.tasktracker.dto.jwt.SaveRefreshTokenDto
import org.esc.tasktracker.entities.JwtTokensStorage
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
fun interface JwtTokensStorageMapper {
    fun tokenFromSaveRefreshDto(o: SaveRefreshTokenDto): JwtTokensStorage
}