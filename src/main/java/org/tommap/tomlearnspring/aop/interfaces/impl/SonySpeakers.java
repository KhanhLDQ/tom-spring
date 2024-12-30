package org.tommap.tomlearnspring.aop.interfaces.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.tommap.tomlearnspring.aop.interfaces.Speakers;
import org.tommap.tomlearnspring.aop.model.Song;

@Component
@Primary
public class SonySpeakers implements Speakers {
    @Override
    public String makeSound(Song song){
        return "Playing the song "+ song.getTitle()+ " by "
                + song.getSingerName()+
                " with Sony speakers";
    }
}
