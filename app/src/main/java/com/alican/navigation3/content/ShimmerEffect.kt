package com.alican.navigation3.content

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


/**
 * Displays a placeholder for a home movie widget with a shimmer effect.
 * This widget consists of a title placeholder and a horizontal list of movie item placeholders.
 *
 * @param itemCount The number of placeholder movie items to display in the horizontal list.
 * @param modifier The modifier to be applied to the root Column of this widget.
 * @param contentPadding Padding to be applied around the content of the LazyRow.
 * @param verticalArrangement The vertical arrangement of the elements within the root Column.
 * @param itemContent A composable lambda that defines how each individual shimmer item
 *                    in the LazyRow should be rendered. It receives a [Brush] to apply the shimmer effect.
 */
@Composable
fun ShimmerHomeMovieWidgetPlaceholder(
    itemCount: Int,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(vertical = 16.dp),
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(12.dp),
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(12.dp),
    itemContent: @Composable (brush: Brush) -> Unit
) {
    val brush = rememberShimmerBrush()
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = verticalArrangement
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(
                modifier = Modifier
                    .height(20.dp)
                    .fillMaxWidth(0.4f)
                    .background(brush)
            )
            Spacer(
                modifier = Modifier
                    .height(16.dp)
                    .fillMaxWidth(0.2f)
                    .background(brush)
            )
        }
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = contentPadding,
            horizontalArrangement = horizontalArrangement
        ) {
            items(itemCount) {
                itemContent(brush)
            }
        }
    }
}


@Composable
fun rememberShimmerBrush(
    targetValue: Float = 1000f,
    shimmerColor: Color = Color.LightGray,
    backgroundColor: Color = Color.Gray.copy(alpha = 0.3f)
): Brush {


    val shimmerColors = listOf(
        backgroundColor,
        shimmerColor.copy(alpha = 0.6f),
        backgroundColor
    )

    val transition = rememberInfiniteTransition(label = "shimmer_transition")
    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = targetValue,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "shimmer_translate_animation"
    )

    return Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnimation.value, y = translateAnimation.value)
    )
}