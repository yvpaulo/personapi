/*package com.restapi.personapi.mapper;

public interface PersonMapper {
}
*/
package com.restapi.personapi.mapper;

import com.restapi.personapi.dto.PersonDTO;
import com.restapi.personapi.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class); //Mappers.getMapper(PersonMapper.class);

    //@Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
    @Mapping(source = "birthDate",target = "birthDate", dateFormat = "dd-MM-yyyy")
    Person toModel(PersonDTO personDTO);

    PersonDTO toDTO(Person person);


}
