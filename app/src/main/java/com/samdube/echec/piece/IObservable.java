package com.samdube.echec.piece;

public interface IObservable {
    void Subscribe(IObserver p_observateur);
    void Notify();
    void Unsubscribe(IObserver p_observateur);
}
