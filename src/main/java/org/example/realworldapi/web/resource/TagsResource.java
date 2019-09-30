package org.example.realworldapi.web.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.realworldapi.domain.entity.persistent.Tag;
import org.example.realworldapi.domain.resource.service.TagsService;
import org.example.realworldapi.web.dto.TagsDTO;
import org.example.realworldapi.web.qualifiers.NoWrapRootValueObjectMapper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/tags")
public class TagsResource {

  private TagsService tagsService;
  private ObjectMapper objectMapper;

  public TagsResource(
      TagsService tagsService, @NoWrapRootValueObjectMapper ObjectMapper objectMapper) {
    this.tagsService = tagsService;
    this.objectMapper = objectMapper;
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getTags() throws JsonProcessingException {
    List<Tag> tags = tagsService.findTags();
    return Response.ok(objectMapper.writeValueAsString(new TagsDTO(tags)))
        .status(Response.Status.OK)
        .build();
  }
}