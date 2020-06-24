package jp.dely.koach

interface IAnimationConnectionStrategy {

    fun nextAnimation(currentValue: Float, next: IAnimation): IAnimation
}