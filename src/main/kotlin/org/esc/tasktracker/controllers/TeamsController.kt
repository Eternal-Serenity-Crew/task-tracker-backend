package org.esc.tasktracker.controllers

import org.esc.tasktracker.dto.filters.TeamsFilterDto
import org.esc.tasktracker.dto.teams.CreateTeamDto
import org.esc.tasktracker.dto.teams.UpdateTeamDto
import org.esc.tasktracker.entities.Teams
import org.esc.tasktracker.extensions.toHttpResponse
import org.esc.tasktracker.interfaces.CrudController
import org.esc.tasktracker.io.BasicSuccessfulResponse
import org.esc.tasktracker.services.TeamsService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

/**
 * REST controller for managing team resources.
 *
 * This controller provides HTTP endpoints for CRUD operations on teams, implementing
 * [CrudController] interface for standard operations. All endpoints are mapped under
 * the "/api/v1/teams" base path.
 *
 * ## Endpoints Overview:
 * - **GET /api/v1/teams** - Get paginated list of teams with optional filters (by name, owner ID)
 * - **GET /api/v1/teams/{id}** - Get a specific team by ID
 * - **POST /api/v1/teams** - Create a new team
 * - **PATCH /api/v1/teams** - Update an existing team
 * - **DELETE /api/v1/teams** - Delete all teams
 * - **DELETE /api/v1/teams/{id}** - Delete a specific team by ID
 *
 * ## Validation:
 * - Request bodies for creation should include valid team data
 * - Owner user must exist in the system (validated by service layer)
 * - Team names are automatically trimmed by entity listener
 *
 * @param service Service layer for team operations
 *
 * @see RestController
 * @see RequestMapping
 * @see CrudController
 * @see TeamsService
 * @see Teams
 * @see CreateTeamDto
 * @see UpdateTeamDto
 * @see TeamsFilterDto
 *
 * @author Vladimir Fokin
 * @since 0.2.0
 */
@RestController
@RequestMapping("/api/v1/teams")
class TeamsController(private val service: TeamsService) :
    CrudController<Teams, Long, CreateTeamDto, UpdateTeamDto, TeamsFilterDto> {

    @GetMapping
    override fun getAll(
        @ModelAttribute filters: TeamsFilterDto,
        pageable: Pageable
    ): Page<Teams> = service.getAll(filters, pageable)

    @GetMapping("/{id}")
    override fun getById(@PathVariable id: Long): Teams =
        service.getById(id, throwable = true, message = "Команда с ID $id не найдена.")!!

    @PostMapping
    override fun create(@RequestBody item: CreateTeamDto): Teams = service.create(item)

    @PatchMapping
    override fun update(item: UpdateTeamDto): Teams = service.update(item)

    @DeleteMapping
    override fun deleteAll(): BasicSuccessfulResponse<String> = service.deleteAll().toHttpResponse()

    @DeleteMapping("/{id}")
    override fun deleteById(@PathVariable id: Long): BasicSuccessfulResponse<String> =
        service.deleteById(id).toHttpResponse()
}