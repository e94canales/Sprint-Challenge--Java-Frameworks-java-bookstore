package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.exceptions.ResourceNotFoundException;
import com.lambdaschool.foundation.models.Section;
import com.lambdaschool.foundation.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "sectionService")
public class SectionServiceImpl implements SectionService {

    @Autowired
    private SectionRepository sectionRepository;

    @Transactional
    @Override
    public Section save(Section section) {
        Section newSection = new Section();

        if (section.getSectionid() != 0) {
            Section existingSection = sectionRepository.findById(section.getSectionid())
                    .orElseThrow(() -> new ResourceNotFoundException("Section id " + section.getSectionid() + " not found!"));


            newSection.setSectionid(section.getSectionid());
        }

        newSection.setSectionname(section.getSectionname());

        return sectionRepository.save(newSection);
    }
}
