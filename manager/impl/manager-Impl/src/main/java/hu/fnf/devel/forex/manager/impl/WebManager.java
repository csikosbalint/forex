/*
 * The hu.fnf.devel.forex.manager.impl.WebManager
 * is part of the forex project.
 * Copyright (C) 2014 johnnym
 *
 *     This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the
 * Free Software Foundation; either version 2 of the License, or any later
 * version.
 *
 *     This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 *
 *     You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */

package hu.fnf.devel.forex.manager.impl;

import hu.fnf.devel.forex.manager.api.Manager;

/**
 * Created by johnnym on 21/12/14.
 */
public class WebManager implements Manager {
    @Override
    public String sayHello() {
        return "Hello";
    }

    @Override
    public void initMethod() {
        System.out.println("init manager");
    }
}
