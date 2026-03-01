package org.esc.tasktracker.services

import org.esc.tasktracker.dto.filters.TeamsFilterDto
import org.esc.tasktracker.dto.teams.CreateTeamDto
import org.esc.tasktracker.dto.teams.UpdateTeamDto
import org.esc.tasktracker.entities.Teams
import org.esc.tasktracker.interfaces.CrudService
import org.esc.tasktracker.mappers.TeamsMapper
import org.esc.tasktracker.repositories.TeamsRepository
import org.esc.tasktracker.repositories.specs.TeamsSpecifications
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TeamsService(
    override val repository: TeamsRepository,
    private val teamsMapper: TeamsMapper,
    private val teamsSpecifications: TeamsSpecifications,
    private val usersService: UsersService
) :
    CrudService<Teams, Long, CreateTeamDto, UpdateTeamDto, TeamsFilterDto> {

    override fun getAll(
        filters: TeamsFilterDto?,
        pageable: Pageable
    ): Page<Teams> {
        val specs = listOfNotNull(
            teamsSpecifications.hasName(filters?.name),
            teamsSpecifications.hasOwnerId(filters?.ownerId),
        )

        return repository.findAll(Specification.allOf(specs), pageable)
    }

    fun getByUserId(userId: Long, pageable: Pageable): Page<Teams> {
        return usersService.getById(userId, throwable = true, message = "Пользователь с ID $userId не найден.")!!
            .let { user -> repository.findByOwner(user, pageable) }
    }

    @Transactional
    override fun create(item: CreateTeamDto): Teams {
        val user = usersService.getById(
            item.userId,
            throwable = true,
            message = "Пользователь с ID ${item.userId} не найден."
        )!!

        return repository.save(teamsMapper.teamFromDto(item, user))
    }

    @Transactional
    override fun update(item: UpdateTeamDto): Teams {
        val team = getById(item.id, message = "Команда с ID ${item.id} не найдена")!!

        item.name?.let { team.name = it.trim() }
        item.description?.let { team.description = it }
        item.userId?.let {
            val user = usersService.getById(
                it,
                throwable = true,
                message = "Пользователь с ID ${item.userId} не найден."
            )!!

            team.owner = user
        }

        return repository.save(team)
    }

    @Transactional
    override fun deleteById(id: Long): String {
        getById(id, message = "Команда с ID $id не найдена")?.let {
            repository.deleteById(id)
        }

        return "Команда удалена успешно."
    }

    @Transactional
    override fun deleteAll(): String {
        repository.deleteAll()
        return "Все команды удалены успешно."
    }
}