package com.distributed.system.project;



import java.awt.Point;
import java.rmi.Remote;

/**
 *
 * @author mostafa slim
 */
public interface IFactory extends Remote{
    
 public void createSensor(int type, Point position, int service, int capacity, double range);   
}
