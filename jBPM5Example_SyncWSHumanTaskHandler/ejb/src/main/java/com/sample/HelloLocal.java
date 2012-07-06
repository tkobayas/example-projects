package com.sample;

import javax.ejb.Local;


@Local
public interface HelloLocal
{
    public long startProcess() throws Exception;
    public String retrieveTaskByJohn() throws Exception;
    public String retrieveTaskByMary() throws Exception;
}
