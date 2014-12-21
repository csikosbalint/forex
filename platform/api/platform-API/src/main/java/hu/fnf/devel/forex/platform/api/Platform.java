/*
 * The hu.fnf.devel.forex.platform.api.Platform
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

package hu.fnf.devel.forex.platform.api;

/**
 * Created by johnnym on 08/12/14.
 * This service is intended to provide the forex platform service.
 */

public interface Platform {

    /**
     * This method is intended to be called if the init-method of the bean is defined.
     *
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    public void initBundle() throws IllegalAccessException, InstantiationException, ClassNotFoundException;

}
