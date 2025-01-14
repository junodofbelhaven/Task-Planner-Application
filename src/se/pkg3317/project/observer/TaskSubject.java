/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.pkg3317.project.observer;

/**
 *
 * @author anil
 */
public interface TaskSubject {
    void addObserver(TaskObserver o);
    void removeObserver(TaskObserver o);
    void notifyObservers();
}
