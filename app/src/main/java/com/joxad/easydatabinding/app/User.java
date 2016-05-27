package com.joxad.easydatabinding.app;

import com.joxad.easydatabinding.BindableModel;

import lombok.Data;
import lombok.experimental.Builder;

/**
 * Created by josh on 13/04/16.
 */
@Data
@Builder
@BindableModel
public class User {
    private String name;

}