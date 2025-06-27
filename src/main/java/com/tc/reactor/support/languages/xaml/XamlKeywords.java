package com.tc.reactor.support.languages.xaml;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XamlKeywords {

    public static final Map<String, List<String>> ELEMENT_KEYWORDS = new HashMap<>();
    public static final Map<String, List<String>> ATTRIBUTE_KEYWORDS = new HashMap<>();
    public static final Map<String, List<String>> HAMILTON_KEYWORDS = new HashMap<>();
    public static final Map<String, List<String>> TECAN_KEYWORDS = new HashMap<>();

    static {
        ELEMENT_KEYWORDS.put("xaml", Arrays.asList("Window", "Resources", "Grid", "Border", "RowDefinitions", "RowDefinition",
                "ControlTemplate", "ContentPresenter", "DropShadowEffect", "BitmapImage", "ColumnDefinitions", "ColumnDefinition",
                "TextBlock","Path","Triggers", "Trigger", "Value", "Setter", "BulletDecorator"));
        ATTRIBUTE_KEYWORDS.put("xaml", Arrays.asList("AllowsTransparency", "WindowStyle", "ResizeMode", "Background",
                "FontFamily", "Title", "Width", "Height", "xmlns", "SolidColorBrush", "Key", "SoundFile", "Value", "BindingMode",
                "PropertyName", "ElementName", "VariableName", "Boolean", "NewValue", "EventPath", "ValueType", "Margin",
                "BorderThickness", "CornerRadius", "BorderBrush", "StaticResource", "DynamicResource", "Content", "ContentTemplate",
                "ContentStringFormat", "TemplateBinding", "ContentStringFormat", "HorizontalAlignment", "VerticalAlignment",
                "ClosesDialog", "ReturnValue", "IsCancel", "FontSize", "Name", "Cursor", "Opacity", "Canvas", "Left", "Right",
                "Top", "Bottom", "TargetType", "Foreground", "IsDefault", "IsEnabled", "Text", "FontStyle", "FontWeight",
                "TextWrapping", "Maximum", "MaximumText", "Minimum", "MinimumText", "ValueString", "ShadowDepth", "Color",
                "BlurRadius", "BaseUri", "UriSource", "CacheOption", "NewValue", "Stretch", "GroupName","Data","Fill",
                "Column","Row","RowSpan","ColumnSpan", "Property"));
        HAMILTON_KEYWORDS.put("xaml", Arrays.asList("hhcdf", "HxVariableBinding", "HxSound", "HxTheme", "HxEventTrigger",
                "HxPropertySetter", "HxCanvasCtrl", "HxButtonCtrl", "Template", "HxTextBlockCtrl", "HxGroupBoxCtrl",
                "HxNumericUpDownCtrl", "ToolTip", "HxToolTipCtrl", "ShowMinMax", "HxCheckBoxCtrl", "HxImageCtrl", "Effect",
                "Source", "Header", "HxRadioButtonCtrl"));
        TECAN_KEYWORDS.put("xaml", Arrays.asList(""));
    }
}
