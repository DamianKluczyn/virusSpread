package com.example.virusspread.model.Vector2d;

import java.util.ArrayList;

public interface IVector2D {
    double abs();
    double cdot(IVector2D param);
    ArrayList<Double> getComponents();
}
